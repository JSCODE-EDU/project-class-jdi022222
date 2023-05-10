package com.comibird.anonymousforum.common.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {

    public static final ResponseEntity<String> POST_NOT_FOUND =
            new ResponseEntity<>("존재하지 않는 게시글입니다.", HttpStatus.BAD_REQUEST);
}
