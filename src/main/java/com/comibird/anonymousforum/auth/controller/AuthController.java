package com.comibird.anonymousforum.auth.controller;

import com.comibird.anonymousforum.auth.dto.request.LoginRequest;
import com.comibird.anonymousforum.auth.dto.request.LogoutRequest;
import com.comibird.anonymousforum.auth.dto.request.TokenRequest;
import com.comibird.anonymousforum.auth.dto.response.TokenResponse;
import com.comibird.anonymousforum.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@Valid @RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.reissue(tokenRequest));
    }
}
