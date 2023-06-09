package com.comibird.anonymousforum.comment.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long commentId;
    private String content;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    private CommentResponse(Long commentId, String content, String email, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .email(comment.getUser().getEmail())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
