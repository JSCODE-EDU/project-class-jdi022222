package com.comibird.anonymousforum.heart.exception;

import com.comibird.anonymousforum.common.exception.CustomNotFoundException;

public class HeartNotFoundException extends CustomNotFoundException {

    private static final String Message = "좋아요가 되어있지 않습니다.";

    public HeartNotFoundException() {
        super(Message);
    }
}
