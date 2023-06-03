package com.comibird.anonymousforum.heart.exception;

import com.comibird.anonymousforum.common.exception.CustomBadRequestException;

public class AlreadyHeartException extends CustomBadRequestException {

    private static final String MESSAGE = "이미 좋아요를 눌렀습니다.";

    public AlreadyHeartException() {
        super(MESSAGE);
    }
}
