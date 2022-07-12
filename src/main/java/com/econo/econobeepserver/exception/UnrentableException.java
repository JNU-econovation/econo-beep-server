package com.econo.econobeepserver.exception;

public class UnrentableException extends Exception {
    public UnrentableException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.UNRENTABLE_EXCEPTION.getMessage();
    }
}
