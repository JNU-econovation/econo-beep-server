package com.econo.econobeepserver.exception;

public class ExpiredAccessTokenException extends RuntimeException {
    public ExpiredAccessTokenException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.EXPIRED_ACCESS_TOKEN_EXCEPTION.getMessage();
    }
}
