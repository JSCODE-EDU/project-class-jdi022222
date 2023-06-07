package com.comibird.anonymousforum.auth.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReissueRequest {

    @NotEmpty(message = "accessToken 값을 입력해주세요.")
    private String accessToken;

    @NotEmpty(message = "refreshToken 값을 입력해주세요.")
    private String refreshToken;
}
