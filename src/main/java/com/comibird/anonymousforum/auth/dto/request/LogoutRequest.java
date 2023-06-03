package com.comibird.anonymousforum.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class LogoutRequest {

    @NotEmpty(message = "accessToken 값을 입력해주세요.")
    private String accessToken;
}
