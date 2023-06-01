package com.comibird.anonymousforum.heart.controller;

import com.comibird.anonymousforum.auth.util.SecurityUtil;
import com.comibird.anonymousforum.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> addHeart(@PathVariable Long postId) {
        heartService.addHeart(SecurityUtil.getCurrentMemberId(), postId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteHeart(@PathVariable Long postId) {
        heartService.deleteHeart(SecurityUtil.getCurrentMemberId(), postId);
        return ResponseEntity.ok().build();
    }
}
