package com.comibird.anonymousforum.authentication.dto.response;

import lombok.Getter;

@Getter
public class LonginTokenResponse {

    private String token;

    public LonginTokenResponse(String token) {
        this.token = token;
    }
}
