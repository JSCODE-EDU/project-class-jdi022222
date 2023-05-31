package com.comibird.anonymousforum.comment.service;

import com.comibird.anonymousforum.auth.exception.UnauthorizedAccessException;
import com.comibird.anonymousforum.comment.domain.Comment;
import com.comibird.anonymousforum.comment.dto.request.CommentCreateRequestDTO;
import com.comibird.anonymousforum.comment.repository.CommentRepository;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.post.exception.PostNotFoundException;
import com.comibird.anonymousforum.post.repository.PostRepository;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(Long userId, Long postId, CommentCreateRequestDTO commentCreateRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(commentCreateRequestDTO.getContent())
                .build();
        commentRepository.save(comment);
    }
}
