package com.comibird.anonymousforum.post.service;

import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.post.dto.request.PostCreateRequestDTO;
import com.comibird.anonymousforum.post.dto.response.PostResponseDTO;
import com.comibird.anonymousforum.post.dto.response.PostResponsesDTO;
import com.comibird.anonymousforum.post.exception.PostNotFoundException;
import com.comibird.anonymousforum.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void save(PostCreateRequestDTO requestDTO) {
        Post post = requestDTO.toEntity();
        postRepository.save(post);
    }

    /**
     * 게시글 전체 조회
     * 최근 100개 limit
     *
     * @return PostResponsesDTO
     */
    @Transactional(readOnly = true)
    public PostResponsesDTO findPosts() {
        List<Post> posts = postRepository.findTop100ByOrderByCreatedAtDesc();

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
        return PostResponseDTO.from(post);
    }

    /**
     * 게시글 수정
     *
     * @param id
     * @param requestDTO title, content 수정
     */
    @Transactional
    public void editPostById(Long id, PostCreateRequestDTO requestDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        post.updatePost(requestDTO.getTitle(), requestDTO.getContent());
    }

    /**
     * id로 게시글 삭제
     *
     * @param id
     */
    @Transactional
    public void deletePostById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
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
        List<Post> posts = postRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);
        return PostResponsesDTO.of(posts);
    }
}
