package com.comibird.anonymousforum.comment.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private String content;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    private CommentResponse(String content, String email, LocalDateTime createdAt) {
        this.content = content;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .email(comment.getUser().getEmail())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
