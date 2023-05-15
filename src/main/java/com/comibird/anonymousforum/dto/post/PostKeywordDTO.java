package com.comibird.anonymousforum.dto.post;

import com.comibird.anonymousforum.common.exception.post.InvalidPostKeywordException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class PostKeywordDTO {
    private String keyword;

    public PostKeywordDTO(String keyword) {
        this.keyword = keyword;
    }

    public void validateKeywordData() {
        if (!StringUtils.hasText(keyword)) {
            throw new InvalidPostKeywordException("검색 키워드는 공백을 제외한 1글자 이상이어야 합니다.");
        }
    }
}
