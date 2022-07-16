package com.econo.econobeepserver.exception;

public class NotFoundRenteeException extends RuntimeException {
    public NotFoundRenteeException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage();
    }
}
