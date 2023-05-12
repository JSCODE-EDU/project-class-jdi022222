package com.comibird.anonymousforum.common.utils.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {

    public static final ResponseEntity<String> POST_NOT_FOUND =
            new ResponseEntity<>("게시글을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
}
