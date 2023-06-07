package com.comibird.anonymousforum.redis.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, Object> refreshTokenTemplate; // 리프레시 토큰 저장용 RedisTemplate
    private final RedisTemplate<String, Object> accessTokenTemplate; // 액세스 토큰 블랙리스트 저장용 RedisTemplate

    public void setRefreshToken(String userId, String refreshToken, long milliseconds) {
        String key = "refresh_token:" + userId;
        refreshTokenTemplate.opsForValue().set(key, refreshToken, milliseconds, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userId) {
        String key = "refresh_token:" + userId;
        return String.valueOf(refreshTokenTemplate.opsForValue().get(key));
    }

    public boolean deleteRefreshToken(String userId) {
        String key = "refresh_token:" + userId;
        Boolean isDeleted = refreshTokenTemplate.delete(key);
        return isDeleted != null && isDeleted;
    }

    public void setAccessTokenBlacklist(String userId, String accessToken, long milliseconds) {
        String key = "access_token:" + userId;
        accessTokenTemplate.opsForValue().set(key, accessToken, milliseconds, TimeUnit.MILLISECONDS);
    }

    public String getAccessToken(String userId) {
        String key = "access_token:" + userId;
        return String.valueOf(accessTokenTemplate.opsForValue().get(key));
    }
}

