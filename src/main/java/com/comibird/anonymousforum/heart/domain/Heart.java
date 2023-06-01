package com.comibird.anonymousforum.heart.domain;

import com.comibird.anonymousforum.common.domain.BaseTimeEntity;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.user.domain.Authority;
import com.comibird.anonymousforum.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Heart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Heart(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
