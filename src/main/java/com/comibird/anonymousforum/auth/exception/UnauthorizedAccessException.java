package com.comibird.anonymousforum.auth.exception;

import com.comibird.anonymousforum.common.exception.CustomBadRequestException;

public class UnauthorizedAccessException extends CustomBadRequestException {
    private static final String MESSAGE = "허락되지 않은 요청입니다.";

    public UnauthorizedAccessException() {
        super(MESSAGE);
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
