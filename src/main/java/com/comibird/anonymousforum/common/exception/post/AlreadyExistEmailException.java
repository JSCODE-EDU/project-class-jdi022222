package com.comibird.anonymousforum.common.exception.post;

public class AlreadyExistEmailException extends RuntimeException{

    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
