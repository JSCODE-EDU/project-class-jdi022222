package com.comibird.anonymousforum.user.controller;


import com.comibird.anonymousforum.user.dto.request.UserCreateRequestDTO;
import com.comibird.anonymousforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> singUp(@Valid @RequestBody UserCreateRequestDTO requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
