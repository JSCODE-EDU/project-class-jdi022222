package com.comibird.anonymousforum.comment.dto.response;

import com.comibird.anonymousforum.comment.domain.Comment;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.post.dto.response.PostResponseDTO;
import com.comibird.anonymousforum.post.dto.response.PostResponsesDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentResponsesDTO {
    private final List<CommentResponseDTO> commentResponsesDTOList;

    @Builder
    private CommentResponsesDTO(List<CommentResponseDTO> commentResponseDTOList) {
        this.commentResponsesDTOList = commentResponseDTOList;
    }

    public static CommentResponsesDTO of(List<Comment> commentList) {
        return CommentResponsesDTO.builder()
                .commentResponseDTOList(commentList.stream()
                        .map(CommentResponseDTO::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
