package com.comibird.anonymousforum.user.exception;

import com.comibird.anonymousforum.common.exception.CustomNotFoundException;

public class UserNotFoundException extends CustomNotFoundException {

    public UserNotFoundException() {
        super("해당하는 회원을 찾을 수 없습니다.");
    }
}
