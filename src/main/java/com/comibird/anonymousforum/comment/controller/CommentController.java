package com.comibird.anonymousforum.comment.controller;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.comment.dto.request.CommentCreateRequestDTO;
import com.comibird.anonymousforum.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> addComment(@PathVariable Long postId, @Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO){
        commentService.save(SecurityUtil.getCurrentMemberId(), postId, commentCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
