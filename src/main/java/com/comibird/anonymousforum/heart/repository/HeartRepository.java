package com.comibird.anonymousforum.heart.repository;

import com.comibird.anonymousforum.heart.domain.Heart;
import com.comibird.anonymousforum.post.domain.Post;
import com.comibird.anonymousforum.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPost(User user, Post post);

    void deleteByUser(User user);
}
