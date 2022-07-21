package com.econo.econobeepserver.exception;

public class UnreturnableException extends RuntimeException{
    public UnreturnableException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.UNRETURNABLE_EXCEPTION.getMessage();
    }
}
