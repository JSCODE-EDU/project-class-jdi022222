package com.comibird.anonymousforum.user.controller;


import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.user.dto.request.UserCreateRequest;
import com.comibird.anonymousforum.user.dto.response.UserResponse;
import com.comibird.anonymousforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> singUp(@Valid @RequestBody UserCreateRequest requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> findUserInfoById() {
        return ResponseEntity.ok(userService.findUserInfoById(SecurityUtil.getCurrentMemberId()));
    }
}
