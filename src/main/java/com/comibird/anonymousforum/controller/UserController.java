package com.comibird.anonymousforum.controller;


import com.comibird.anonymousforum.dto.member.UserCreateRequestDTO;
import com.comibird.anonymousforum.dto.member.UserResponseDTO;
import com.comibird.anonymousforum.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity singUp(@Valid @RequestBody UserCreateRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
