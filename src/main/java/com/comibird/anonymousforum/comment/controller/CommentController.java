package com.comibird.anonymousforum.comment.controller;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.comment.dto.request.CommentCreateRequest;
import com.comibird.anonymousforum.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Long> addComment(@PathVariable Long postId,
                                           @Valid @RequestBody CommentCreateRequest commentCreateRequest){
        Long savedCommentId = commentService.save(SecurityUtil.getCurrentMemberId(), postId, commentCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(SecurityUtil.getCurrentMemberId(), commentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
