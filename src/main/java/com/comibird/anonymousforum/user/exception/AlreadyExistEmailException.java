package com.comibird.anonymousforum.user.exception;

import com.comibird.anonymousforum.common.exception.CustomBadRequestException;

public class AlreadyExistEmailException extends CustomBadRequestException {

    public AlreadyExistEmailException() {
        super("이미 존재하는 이메일입니다");
    }
}
