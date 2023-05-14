package com.comibird.anonymousforum.dto.post;

import com.comibird.anonymousforum.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}