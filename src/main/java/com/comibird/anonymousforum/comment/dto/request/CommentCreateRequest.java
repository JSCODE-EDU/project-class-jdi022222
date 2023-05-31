package com.comibird.anonymousforum.comment.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentCreateRequest {

    @NotBlank(message = "댓글은 공백이 될 수 없습니다.")
    private String content;
}
