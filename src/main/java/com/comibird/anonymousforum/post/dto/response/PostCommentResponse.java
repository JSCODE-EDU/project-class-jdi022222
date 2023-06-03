package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.comibird.anonymousforum.comment.dto.response.CommentResponses;
import com.comibird.anonymousforum.heart.domain.Heart;
import com.comibird.anonymousforum.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class PostCommentResponse {

    private Long postId;
    private String userEmail;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private CommentResponses commentResponses;
    private int heartCount;

    @Builder
    private PostCommentResponse(Long postId, String email, String title, String content, LocalDateTime createdAt, List<Comment> comments, Set<Heart> hearts) {
        this.postId = postId;
        this.userEmail = email;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.commentResponses = CommentResponses.of(comments);
        this.heartCount = hearts.size();
    }

    public static PostCommentResponse from(Post post, List<Comment> comments) {
        return PostCommentResponse.builder()
                .postId(post.getId())
                .email(post.getUser().getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .comments(comments)
                .hearts(post.getHearts())
                .build();
    }
}
