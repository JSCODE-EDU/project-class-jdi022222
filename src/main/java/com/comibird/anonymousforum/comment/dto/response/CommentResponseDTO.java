package com.comibird.anonymousforum.comment.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private String content;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    private CommentResponseDTO(String content, String email, LocalDateTime createdAt) {
        this.content = content;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static CommentResponseDTO from(Comment comment) {
        return CommentResponseDTO.builder()
                .content(comment.getContent())
                .email(comment.getUser().getEmail())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
