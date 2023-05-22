package com.comibird.anonymousforum.user.exception;

public class AlreadyExistEmailException extends RuntimeException{

    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
