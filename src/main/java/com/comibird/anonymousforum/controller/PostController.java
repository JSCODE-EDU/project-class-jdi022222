package com.comibird.anonymousforum.controller;

import com.comibird.anonymousforum.service.PostResponseDTO;
import com.comibird.anonymousforum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
