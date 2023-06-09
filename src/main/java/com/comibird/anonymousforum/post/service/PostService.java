package com.comibird.anonymousforum.post.service;

import com.comibird.anonymousforum.auth.exception.UnauthorizedAccessException;
import com.comibird.anonymousforum.comment.domain.Comment;
import com.comibird.anonymousforum.comment.repository.CommentRepository;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.post.dto.request.PostCreateRequest;
import com.comibird.anonymousforum.post.dto.response.PostCommentResponse;
import com.comibird.anonymousforum.post.dto.response.PostResponses;
import com.comibird.anonymousforum.post.exception.PostNotFoundException;
import com.comibird.anonymousforum.post.repository.PostRepository;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void save(Long userId, PostCreateRequest requestDTO) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponses findPosts(Pageable pageable) {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        return PostResponses.of(posts);
    }

    @Transactional(readOnly = true)
    public PostCommentResponse findPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return PostCommentResponse.from(post);
    }

    @Transactional
    public void editPostById(Long userId, Long postId, PostCreateRequest requestDTO) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        validatePostOwner(userId, post);
        post.updatePost(requestDTO.getTitle(), requestDTO.getContent());
    }

    @Transactional
    public void deletePostById(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        validatePostOwner(userId, post);
        postRepository.deleteById(postId);
    }

    @Transactional(readOnly = true)
    public PostResponses findPostsByKeyword(String keyword, Pageable pageable) {
        List<Post> posts = postRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword, pageable);
        return PostResponses.of(posts);
    }

    @Transactional(readOnly = true)
    public void validatePostOwner(Long userId, Post post) {
        if (post.getId() != userId) {
            throw new UnauthorizedAccessException("본인의 게시글만 접근할 수 있습니다.");
        }
    }
}
