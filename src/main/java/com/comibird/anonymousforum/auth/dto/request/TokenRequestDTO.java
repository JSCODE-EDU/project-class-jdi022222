package com.comibird.anonymousforum.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDTO {
    private String accessToken;
    private String refreshToken;
}
