package com.comibird.anonymousforum.auth.controller;

import com.comibird.anonymousforum.auth.dto.request.LoginRequestDTO;
import com.comibird.anonymousforum.auth.dto.request.TokenRequestDTO;
import com.comibird.anonymousforum.auth.dto.response.TokenResponseDTO;
import com.comibird.anonymousforum.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @DeleteMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable String id) {
        authService.logout(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDTO> reissue(@RequestBody TokenRequestDTO tokenRequestDTO) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDTO));
    }
}
