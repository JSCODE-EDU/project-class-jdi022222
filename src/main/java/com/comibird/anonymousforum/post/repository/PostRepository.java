package com.comibird.anonymousforum.post.repository;

import com.comibird.anonymousforum.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"hearts", "comments"})
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    @EntityGraph(attributePaths = {"hearts", "comments"})
    List<Post> findAllByTitleContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);

}
