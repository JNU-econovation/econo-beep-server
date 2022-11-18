package com.econo.econobeepserver.exception;

public class DuplicatedBookmarkException extends RuntimeException {
    public DuplicatedBookmarkException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.DUPLICATED_BOOKMARK_EXCEPTION.getMessage();
    }
}
