package com.comibird.anonymousforum.post.domain;

import com.comibird.anonymousforum.common.domain.BaseTimeEntity;
import com.comibird.anonymousforum.heart.domain.Heart;
import com.comibird.anonymousforum.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    Set<Heart> hearts = new HashSet<>();

    @Builder
    private Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}