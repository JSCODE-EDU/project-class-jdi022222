package com.comibird.anonymousforum.post.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostKeyword {

    @NotBlank(message = "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다.")
    private String keyword;

    public PostKeyword(String keyword) {
        this.keyword = keyword;
    }
}
