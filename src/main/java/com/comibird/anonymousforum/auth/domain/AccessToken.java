package com.comibird.anonymousforum.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "access_token")
public class AccessToken {

    @Id
    private String key;

    private String value;

    @TimeToLive
    private Long expired;

    @Builder
    private AccessToken(String key, String value, Long expired) {
        this.key = key;
        this.value = value;
        this.expired = expired;
    }
}
