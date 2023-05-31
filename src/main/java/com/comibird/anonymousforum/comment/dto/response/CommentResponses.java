package com.comibird.anonymousforum.comment.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentResponses {
    private final List<CommentResponse> commentResponses;

    @Builder
    private CommentResponses(List<CommentResponse> commentResponses) {
        this.commentResponses = commentResponses;
    }

    public static CommentResponses of(List<Comment> comments) {
        return CommentResponses.builder()
                .commentResponses(comments.stream()
                        .map(CommentResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
