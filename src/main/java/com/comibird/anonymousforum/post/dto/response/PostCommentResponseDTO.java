package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.comibird.anonymousforum.comment.dto.response.CommentResponseDTO;
import com.comibird.anonymousforum.comment.dto.response.CommentResponsesDTO;
import com.comibird.anonymousforum.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostCommentResponseDTO {

    private Long postId;
    private String userEmail;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private CommentResponsesDTO commentResponsesDTO;

    @Builder
    private PostCommentResponseDTO(Long postId, String email, String title, String content, LocalDateTime createdAt, List<Comment> comments) {
        this.postId = postId;
        this.userEmail = email;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.commentResponsesDTO = CommentResponsesDTO.of(comments);
    }

    public static PostCommentResponseDTO from(Post post, List<Comment> comments) {
        return PostCommentResponseDTO.builder()
                .postId(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .comments(comments)
                .build();
    }
}
