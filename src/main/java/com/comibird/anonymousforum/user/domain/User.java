package com.comibird.anonymousforum.user.domain;

import com.comibird.anonymousforum.common.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    private User(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}
