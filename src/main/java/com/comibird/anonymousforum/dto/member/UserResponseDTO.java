package com.comibird.anonymousforum.dto.member;

import com.comibird.anonymousforum.domain.member.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDTO {

    private Long id;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signUpTime;

    @Builder
    private UserResponseDTO(final Long id, final String email, final LocalDateTime signUpTime) {
        this.id = id;
        this.email = email;
        this.signUpTime = signUpTime;
    }

    public static UserResponseDTO from(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .signUpTime(user.getCreatedAt())
                .build();
    }
}
