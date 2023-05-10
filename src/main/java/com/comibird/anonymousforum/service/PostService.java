package com.comibird.anonymousforum.service;

import com.comibird.anonymousforum.common.exception.post.PostNotFoundException;
import com.comibird.anonymousforum.controller.PostRequestDTO;
import com.comibird.anonymousforum.domain.post.Post;
import com.comibird.anonymousforum.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        // Post Entity -> Response DTO
        PostResponseDTO postResponseDTO = savedPost.toDTO();

        return postResponseDTO;
    }

    /**
     * 게시글 전체 조회
     * 최근 100개 limit
     *
     * @return List<PostResponseDTO>
     */
    @Transactional(readOnly = true)
    public List<PostResponseDTO> findPosts() {
        // Post 최근 100개 조회
        List<Post> postList = postRepository.findTop100ByOrderByCreatedAtDesc();

        // List<Post> -> List<PostResponseDTO>
        return postList.stream()
                .map(post -> post.toDTO())
                .collect(Collectors.toList());
    }

    /**
     * id로 게시글 조회
     *
     * @param id
     * @return PostResponseDTO
     */
    public PostResponseDTO findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        return post.toDTO();
    }
}
