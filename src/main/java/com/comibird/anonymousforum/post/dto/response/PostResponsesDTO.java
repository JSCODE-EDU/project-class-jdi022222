package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponsesDTO {
    private final List<PostResponseDTO> postResponseList;

    @Builder
    private PostResponsesDTO(List<PostResponseDTO> postResponseList) {
        this.postResponseList = postResponseList;
    }

    public static PostResponsesDTO of(List<Post> posts) {
        return PostResponsesDTO.builder()
                .postResponseList(posts.stream()
                        .map(PostResponseDTO::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
