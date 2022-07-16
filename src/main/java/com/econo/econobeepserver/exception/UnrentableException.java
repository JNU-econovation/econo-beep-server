package com.econo.econobeepserver.exception;

public class UnrentableException extends RuntimeException {
    public UnrentableException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.UNRENTABLE_EXCEPTION.getMessage();
    }
}
