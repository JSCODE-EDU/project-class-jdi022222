package com.comibird.anonymousforum.dto.post;

import com.comibird.anonymousforum.common.exception.post.InvalidPostModificationException;
import com.comibird.anonymousforum.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class PostRequestDTO {

    private Long id;
    private String title;
    private String content;

    @Builder
    private PostRequestDTO(Long orderId, String title, String content) {
        this.id = orderId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

    public void validatePostData() {
        if (!StringUtils.hasText(title)) {
            throw new InvalidPostModificationException("제목은 공백이 될 수 없습니다.");
        }
        if (!StringUtils.hasLength(content)) {
            throw new InvalidPostModificationException("내용을 입력해주세요.");
        }
        if (title.length() < 1 || title.length() > 15) {
            throw new InvalidPostModificationException("제목을 1글자 이상 15글자 이하로 입력해주세요.");
        }
        if (content.length() < 1 || content.length() > 1000) {
            throw new InvalidPostModificationException("내용을 1글자 이상 1000글자 이하로 입력해주세요.");
        }
    }
}