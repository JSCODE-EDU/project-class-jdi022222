package com.comibird.anonymousforum.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
대상 컨트롤러를 지정하지 않았기 때문에 모든 컨트롤러에서 발생하는 예외 처리 (글로벌 적용)
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
}
