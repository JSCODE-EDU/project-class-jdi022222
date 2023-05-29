package com.comibird.anonymousforum.user.controller;


import com.comibird.anonymousforum.common.dto.response.ApiResponseType;
import com.comibird.anonymousforum.common.exception.CustomBadRequestException;
import com.comibird.anonymousforum.user.dto.request.UserCreateRequestDTO;
import com.comibird.anonymousforum.user.dto.response.UserResponseDTO;
import com.comibird.anonymousforum.user.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> singUp(@Valid @RequestBody UserCreateRequestDTO requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponseDTO> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomBadRequestException(ApiResponseType.UNAUTHORIZED_RESPONSE.getMessage());
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        UserResponseDTO userResponseDTO = userService.getUser(email);
        return ResponseEntity.ok(userResponseDTO);
    }
}
