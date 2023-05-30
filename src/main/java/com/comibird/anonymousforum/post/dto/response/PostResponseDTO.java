package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDTO {

    private Long postId;
    private String userEmail;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    private PostResponseDTO(Long postId, String email, String title, String content, LocalDateTime createdAt) {
        this.postId = postId;
        this.userEmail = email;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static PostResponseDTO from(Post post) {
        return PostResponseDTO.builder()
                .postId(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
