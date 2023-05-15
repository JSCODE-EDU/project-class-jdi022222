package com.comibird.anonymousforum.dto.post;

import com.comibird.anonymousforum.common.exception.post.InvalidPostModificationException;
import com.comibird.anonymousforum.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class PostCreateRequestDTO {

    @NotBlank(message = "제목은 공백이 될 수 없습니다.")
    @Size(min = 1, max = 15, message = "제목을 1글자 이상 15글자 이하로 입력해주세요.")
    private String title;

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 1000, message = "내용을 1글자 이상 1000글자 이하로 입력해주세요.")
    private String content;

    @Builder
    private PostCreateRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
