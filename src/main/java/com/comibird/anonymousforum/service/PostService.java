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
        PostResponseDTO postResponseDTO = PostResponseDTO.toDTO(savedPost);

        return postResponseDTO;
    }

    /**
     * 게시글 전체 조회
     * 최근 100개 limit
     *
     * @return PostResponsesDTO
     */
    @Transactional(readOnly = true)
    public PostResponsesDTO findPosts() {
        // Post 최근 100개 조회
        List<Post> posts = postRepository.findTop100ByOrderByCreatedAtDesc();

        // List<Post> -> PostResponsesDTO
        return PostResponsesDTO.of(posts);
    }

    /**
     * id로 게시글 조회
     *
     * @param id
     * @return PostResponseDTO
     */
    @Transactional(readOnly = true)
    public PostResponseDTO findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        return PostResponseDTO.toDTO(post);
    }

    /**
     * 게시글 수정
     *
     * @param id
     * @param requestDTO title, content 수정
     */
    @Transactional
    public void editPostById(Long id, PostRequestDTO requestDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        post.setTitle(requestDTO.getTitle());
        post.setContent(requestDTO.getContent());
    }

    /**
     * id로 게시글 삭제
     *
     * @param id
     */
    @Transactional
    public void deletePostById(Long id) {
        // 삭제할 Post 존재 확인
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());

        // 삭제
        postRepository.deleteById(id);
    }

    /**
     * 키워드로 게시글 최근 100개 조회
     *
     * @param keyword
     * @return PostResponsesDTO
     */
    @Transactional(readOnly = true)
    public PostResponsesDTO findPostsByKeyword(String keyword) {
        // 제목에 키워드를 포함한 Post 최근 100개 조회
        List<Post> posts = postRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);

        // List<Post> -> PostResponsesDTO
        return PostResponsesDTO.of(posts);
    }
}
