package com.comibird.anonymousforum.common.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
