package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.heart.domain.Heart;
import com.comibird.anonymousforum.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class PostResponse {

    private Long postId;
    private String userEmail;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private int heartCount;

    @Builder
    private PostResponse(Long postId, String email, String title, String content, LocalDateTime createdAt, Set<Heart> hearts) {
        this.postId = postId;
        this.userEmail = email;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.heartCount = hearts.size();
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .hearts(post.getHearts())
                .build();
    }
}
