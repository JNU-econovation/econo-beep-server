package com.econo.econobeepserver.exception;

public class NotFoundBookException extends Exception {
    public NotFoundBookException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_FOUND_BOOK_EXCEPTION.getMessage();
    }
}
