package com.comibird.anonymousforum.auth.controller;

import com.comibird.anonymousforum.auth.dto.request.LoginRequest;
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

    @DeleteMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable String id) {
        authService.logout(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody TokenRequest tokenRequestDTO) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDTO));
    }
}
