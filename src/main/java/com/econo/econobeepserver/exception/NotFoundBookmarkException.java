package com.econo.econobeepserver.exception;

public class NotFoundBookmarkException extends RuntimeException {
    public NotFoundBookmarkException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_FOUND_BOOKMARK_EXCEPTION.getMessage();
    }
}
