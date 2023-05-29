package com.comibird.anonymousforum.post.exception;

import com.comibird.anonymousforum.common.exception.CustomNotFoundException;

public class PostNotFoundException extends CustomNotFoundException {
    public PostNotFoundException() {
        super("게시글을 찾을 수 없습니다.");
    }
}

