package com.comibird.anonymousforum.controller;

import com.comibird.anonymousforum.dto.post.PostRequestDTO;
import com.comibird.anonymousforum.dto.post.PostResponseDTO;
import com.comibird.anonymousforum.dto.post.PostResponsesDTO;
import com.comibird.anonymousforum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> addPost(@RequestBody PostRequestDTO requestDTO) {
        PostResponseDTO responseDTO = postService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity getPosts() {
        PostResponsesDTO responseDTO = postService.findPosts();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
        PostResponseDTO responseDTO = postService.findPostById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editPost(@PathVariable Long id, @RequestBody PostRequestDTO requestDTO) {
        postService.editPostById(id, requestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<PostResponsesDTO> getPostsByKeyword(@RequestParam String keyword) {
        PostResponsesDTO responseDTO = postService.findPostsByKeyword(keyword);
        return ResponseEntity.ok(responseDTO);
    }
}
