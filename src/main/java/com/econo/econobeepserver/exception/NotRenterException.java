package com.econo.econobeepserver.exception;

public class NotRenterException extends RuntimeException {
    public NotRenterException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_RENTER_EXCEPTION.getMessage();
    }
}
