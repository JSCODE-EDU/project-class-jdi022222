package com.comibird.anonymousforum.controller;

import com.comibird.anonymousforum.service.PostResponseDTO;
import com.comibird.anonymousforum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostResponseDTO addPost(@RequestBody PostRequestDTO requestDTO) {
        return postService.save(requestDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostResponseDTO> getPosts() {
        return postService.findPosts();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PostResponseDTO getPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }
}
