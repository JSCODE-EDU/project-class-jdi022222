package com.comibird.anonymousforum.post.controller;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.post.dto.request.PostCreateRequest;
import com.comibird.anonymousforum.post.dto.request.PostKeyword;
import com.comibird.anonymousforum.post.dto.response.PostCommentResponse;
import com.comibird.anonymousforum.post.dto.response.PostResponse;
import com.comibird.anonymousforum.post.dto.response.PostResponses;
import com.comibird.anonymousforum.post.service.PostService;
import com.comibird.anonymousforum.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> addPost(@Valid @RequestBody PostCreateRequest requestDTO) {
        postService.save(SecurityUtil.getCurrentMemberId(), requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(operationId = "getPosts", summary = "게시글 전체 조회", description = "모든 게시글을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostResponses.class)))),})
    @GetMapping
    public ResponseEntity getPosts() {
        PostResponses responseDTO = postService.findPosts();
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(operationId = "getPost", summary = "특정 게시글 조회", description = "특정 게시글을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostResponse.class)))),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content()),})
    @GetMapping("/{id}")
    public ResponseEntity<PostCommentResponse> getPost(@PathVariable Long id) {
       PostCommentResponse responseDTO = postService.findPostById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(operationId = "editPost", summary = "특정 게시글 수정", description = "특정 게시글을 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Invalid", content = @Content()),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content()),})
    @PutMapping("/{id}")
    public ResponseEntity<Void> editPost(@PathVariable Long id, @Valid @RequestBody PostCreateRequest requestDTO) {
        postService.editPostById(SecurityUtil.getCurrentMemberId(), id, requestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(operationId = "deletePost", summary = "특정 게시글 삭제", description = "특정 게시글을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content()),
            @ApiResponse(responseCode = "404", description = "NotFound", content = @Content()),})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(SecurityUtil.getCurrentMemberId(), id);
        return ResponseEntity.ok().build();
    }

    @Operation(operationId = "getPostsByKeyword", summary = "키워드로 게시글 조회", description = "키워드로 게시글을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostResponses.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid", content = @Content()),})
    @GetMapping(params = "keyword")
    public ResponseEntity<PostResponses> getPostsByKeyword(@RequestParam("keyword") PostKeyword keywordDTO) {
        PostResponses responseDTO = postService.findPostsByKeyword(keywordDTO.getKeyword().trim());
        return ResponseEntity.ok(responseDTO);
    }
}
