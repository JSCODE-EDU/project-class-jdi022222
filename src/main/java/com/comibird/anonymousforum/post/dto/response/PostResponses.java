package com.comibird.anonymousforum.post.dto.response;

import com.comibird.anonymousforum.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponses {
    private final List<PostResponse> postResponses;

    @Builder
    private PostResponses(List<PostResponse> postResponses) {
        this.postResponses = postResponses;
    }

    public static PostResponses of(List<Post> posts) {
        return PostResponses.builder()
                .postResponses(posts.stream()
                        .map(PostResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
