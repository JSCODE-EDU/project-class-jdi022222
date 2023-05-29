package com.comibird.anonymousforum.post.exception;

import com.comibird.anonymousforum.common.exception.CustomBadRequestException;

public class InvalidPostKeywordException extends CustomBadRequestException {

    public InvalidPostKeywordException(String message) {
        super(message);
    }
}
