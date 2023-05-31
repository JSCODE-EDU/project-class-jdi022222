package com.comibird.anonymousforum.comment.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CommentCreateRequestDTO {

    @NotNull(message = "댓글을 입력해주세요.")
    private String content;
}
