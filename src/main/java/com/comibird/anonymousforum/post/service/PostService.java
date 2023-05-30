package com.comibird.anonymousforum.post.service;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.post.dto.request.PostCreateRequestDTO;
import com.comibird.anonymousforum.post.dto.response.PostResponseDTO;
import com.comibird.anonymousforum.post.dto.response.PostResponsesDTO;
import com.comibird.anonymousforum.post.exception.PostNotFoundException;
import com.comibird.anonymousforum.post.repository.PostRepository;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(Long userId, PostCreateRequestDTO requestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponsesDTO findPosts() {
        List<Post> posts = postRepository.findTop100ByOrderByCreatedAtDesc();

        return PostResponsesDTO.of(posts);
    }

    @Transactional(readOnly = true)
    public PostResponseDTO findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        return PostResponseDTO.from(post);
    }

    @Transactional
    public void editPostById(Long id, PostCreateRequestDTO requestDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        post.updatePost(requestDTO.getTitle(), requestDTO.getContent());
    }

    @Transactional
    public void deletePostById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public PostResponsesDTO findPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);
        return PostResponsesDTO.of(posts);
    }
}
