package com.comibird.anonymousforum.authentication.filter;

import com.comibird.anonymousforum.authentication.dto.request.LoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;

        final LoginRequestDTO loginDTO;
        try {
            // 사용자 요청 정보로 UserPasswordAuthenticationToken 발급
            loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            authRequest = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        setDetails(request, authRequest);

        // AuthenticationManager에게 전달 -> AuthenticationProvider의 인증 메서드 실행
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
