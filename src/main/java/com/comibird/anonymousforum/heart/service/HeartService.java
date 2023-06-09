package com.comibird.anonymousforum.heart.service;

import com.comibird.anonymousforum.heart.domain.Heart;
import com.comibird.anonymousforum.heart.exception.AlreadyHeartException;
import com.comibird.anonymousforum.heart.exception.HeartNotFoundException;
import com.comibird.anonymousforum.heart.repository.HeartRepository;
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
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addHeart(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if (isAlreadyLike(user, post)){
            throw new AlreadyHeartException();
        }

        Heart heart = Heart.builder()
                .user(user)
                .post(post)
                .build();
        post.addHeart(heart);
        heartRepository.save(heart);
    }

    @Transactional
    public void deleteHeart(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if (!isAlreadyLike(user, post)){
            throw new HeartNotFoundException();
        }

        heartRepository.deleteByUser(user);
    }

    @Transactional(readOnly = true)
    public boolean isAlreadyLike(User user, Post post) {
        return !heartRepository.findByUserAndPost(user, post).isEmpty();
    }
}
