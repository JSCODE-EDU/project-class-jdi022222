package com.comibird.anonymousforum.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refresh_token", timeToLive = 60 * 60 * 24 * 7)
public class RefreshToken {

    @Id
    private String key;

    private String value;

    @Builder
    private RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}