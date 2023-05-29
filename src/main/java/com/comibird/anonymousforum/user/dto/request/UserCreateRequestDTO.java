package com.comibird.anonymousforum.user.dto.request;

import com.comibird.anonymousforum.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.comibird.anonymousforum.user.domain.Authority.ROLE_USER;

@Getter
public class UserCreateRequestDTO {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "유효한 이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Size(min = 8, max = 15, message = "비밀번호를 8글자 이상 15글자 이하로 입력해주세요.")
    private String password;

    @Builder
    private UserCreateRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return User.builder()
                .email(email)
                .password(hashedPassword)
                .authority(ROLE_USER)
                .build();
    }
}
