package com.comibird.anonymousforum.auth.service;

import com.comibird.anonymousforum.auth.dto.request.LoginRequest;
import com.comibird.anonymousforum.auth.dto.request.ReissueRequest;
import com.comibird.anonymousforum.auth.dto.response.TokenResponse;
import com.comibird.anonymousforum.auth.exception.UnauthorizedAccessException;
import com.comibird.anonymousforum.auth.jwt.JwtProvider;
import com.comibird.anonymousforum.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final RedisUtil redisUtil;

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String userId = authentication.getName();

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenResponse = jwtProvider.generateToken(authentication);

        // 4. RefreshToken 저장
        String refreshToken = tokenResponse.getRefreshToken();
        Long expiration = jwtProvider.getExpiration(tokenResponse.getRefreshToken());
        redisUtil.setRefreshToken(userId, refreshToken, expiration);

        // 5. 토큰 발급
        return tokenResponse;
    }


    @Transactional
    public void logout(String accessToken) {
        // 1. Access Token 검증
        if (!jwtProvider.validateToken(accessToken)) {
            throw new UnauthorizedAccessException("Access Token이 유효하지 않습니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        String userId = authentication.getName();

        // 2. Access Token 정보 추출 및 만료 시간 계산
        long accessTokenExpiration = jwtProvider.getExpiration(accessToken);
        long remainingTime = accessTokenExpiration - System.currentTimeMillis();

        // 3. Access Token을 Redis 블랙리스트에 등록
        redisUtil.setAccessTokenBlacklist(userId, accessToken, remainingTime);

        // 4. Refresh Token 삭제
        redisUtil.deleteRefreshToken(userId);
    }

    @Transactional
    public TokenResponse reissue(ReissueRequest reissueRequest) {
        // 1. AccessToken, Refresh Token 검증
        if (!jwtProvider.validateToken(reissueRequest.getAccessToken())) {
            throw new UnauthorizedAccessException("Access Token이 유효하지 않습니다.");
        }
        if (!jwtProvider.validateToken(reissueRequest.getRefreshToken())) {
            throw new UnauthorizedAccessException("Refresh Token이 유효하지 않습니다.");
        }

        // 2. 인증정보 가져오기
        Authentication authentication = jwtProvider.getAuthentication(reissueRequest.getAccessToken());
        String userId = authentication.getName();

        // 3. Redis에서 RefreshToken 조회
        String refreshToken = redisUtil.getRefreshToken(userId);
        if (!refreshToken.equals(reissueRequest.getRefreshToken())) {
            throw new UnauthorizedAccessException("Refresh Token이 만료되었습니다.");
        }

        // 3. 새로운 토큰 생성
        TokenResponse tokenResponse = jwtProvider.generateToken(authentication);

        // 4. 저장소 정보 업데이트
        Long expiration = jwtProvider.getExpiration(tokenResponse.getRefreshToken());
        redisUtil.setRefreshToken(userId, reissueRequest.getRefreshToken(), expiration);

        // 5. 토큰 발급
        return tokenResponse;
    }
}
