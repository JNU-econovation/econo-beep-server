package com.econo.econobeepserver.exception;

public class WrongAccessTokenException extends RuntimeException {
    public WrongAccessTokenException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.WRONG_ACCESS_TOKEN_EXCEPTION.getMessage();
    }
}
