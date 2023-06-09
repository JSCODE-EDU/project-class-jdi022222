package com.comibird.anonymousforum.post.controller;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.post.dto.request.PostCreateRequest;
import com.comibird.anonymousforum.post.dto.request.PostKeyword;
import com.comibird.anonymousforum.post.dto.response.PostCommentResponse;
import com.comibird.anonymousforum.post.dto.response.PostResponses;
import com.comibird.anonymousforum.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Tag(name = "PostController", description = "익명 게시판 컨트롤러")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> addPost(@Valid @RequestBody PostCreateRequest requestDTO) {
        Long savedPostId = postService.save(SecurityUtil.getCurrentMemberId(), requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPostId);
    }

    @GetMapping
    public ResponseEntity getPosts(@RequestParam(defaultValue = "10") int limit) {
        limit = Math.min(limit, 100);
        Pageable pageable = PageRequest.of(0, limit);
        PostResponses responseDTO = postService.findPosts(pageable);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentResponse> getPost(@PathVariable Long id) {
        PostCommentResponse responseDTO = postService.findPostById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editPost(@PathVariable Long id, @Valid @RequestBody PostCreateRequest requestDTO) {
        postService.editPostById(SecurityUtil.getCurrentMemberId(), id, requestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(SecurityUtil.getCurrentMemberId(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<PostResponses> getPostsByKeyword(@RequestParam("keyword") PostKeyword keyword,
                                                           @RequestParam(defaultValue = "10") int limit) {
        limit = Math.min(limit, 100);
        Pageable pageable = PageRequest.of(0, limit);
        PostResponses responseDTO = postService.findPostsByKeyword(keyword.getKeyword().trim(), pageable);
        return ResponseEntity.ok(responseDTO);
    }
}
