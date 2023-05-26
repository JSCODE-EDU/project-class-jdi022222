package com.comibird.anonymousforum.authentication.exception;

import com.comibird.anonymousforum.common.exception.CustomNotFoundException;

public class UserNotFoundException extends CustomNotFoundException {

    private static final String MESSAGE = "유저 정보를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
