package com.comibird.anonymousforum.auth.exception;

public class UnauthorizedAccessException extends RuntimeException{
    private static final String MESSAGE = "허락되지 않은 요청입니다.";

    public UnauthorizedAccessException() {
        super(MESSAGE);
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
