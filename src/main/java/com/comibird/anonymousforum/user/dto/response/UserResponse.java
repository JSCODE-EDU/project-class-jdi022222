package com.comibird.anonymousforum.user.dto.response;

import com.comibird.anonymousforum.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private Long id;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signUpTime;

    @Builder
    private UserResponse(final Long id, final String email, final LocalDateTime signUpTime) {
        this.id = id;
        this.email = email;
        this.signUpTime = signUpTime;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .signUpTime(user.getCreatedAt())
                .build();
    }
}
