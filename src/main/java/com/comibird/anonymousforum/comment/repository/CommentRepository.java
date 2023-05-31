package com.comibird.anonymousforum.comment.repository;

import com.comibird.anonymousforum.comment.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Comment> findAllByPostId(Long id);
}
