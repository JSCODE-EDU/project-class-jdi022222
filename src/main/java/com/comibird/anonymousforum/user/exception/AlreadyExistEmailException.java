package com.comibird.anonymousforum.user.exception;

import com.comibird.anonymousforum.common.exception.CustomBadRequestException;

public class AlreadyExistEmailException extends CustomBadRequestException {

    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
