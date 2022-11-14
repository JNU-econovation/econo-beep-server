package com.econo.econobeepserver.exception;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_FOUND_USER_EXCEPTION.getMessage();
    }
}
