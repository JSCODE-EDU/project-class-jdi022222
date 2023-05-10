package com.comibird.anonymousforum.service;

import com.comibird.anonymousforum.controller.PostRequestDTO;
import com.comibird.anonymousforum.domain.post.Post;
import com.comibird.anonymousforum.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성
     *
     * @param requestDTO
     * @return requestDTO
     */
    @Transactional
    public PostResponseDTO save(PostRequestDTO requestDTO) {
        // Request DTO -> Post Entity
        Post post = requestDTO.toEntity();

        // Post 생성
        Post savedPost = postRepository.save(post);
        log.info("post saved:{}", savedPost.getId());
        log.info("post createdAt:{}", savedPost.getCreatedAt());

        // Post Entity -> Response DTO
        PostResponseDTO postResponseDTO = savedPost.toDto();

        return postResponseDTO;
    }
}
