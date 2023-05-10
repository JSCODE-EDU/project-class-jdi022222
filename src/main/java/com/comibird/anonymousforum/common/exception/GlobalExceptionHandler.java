package com.comibird.anonymousforum.common.exception;

import com.comibird.anonymousforum.common.exception.post.PostNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.comibird.anonymousforum.common.utils.ResponseConstants.POST_NOT_FOUND;

/*
대상 컨트롤러를 지정하지 않았기 때문에 모든 컨트롤러에서 발생하는 예외 처리 (글로벌 적용)
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<String> handlePostNotFoundException(
            PostNotFoundException exception) {
        log.debug("게시글을 찾을 수 없습니다.", exception);
        return POST_NOT_FOUND;
    }
}
