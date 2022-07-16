package com.econo.econobeepserver.exception;

public class AlreadyRentedException extends RuntimeException{
    public AlreadyRentedException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.ALREADY_RENTED_EXCEPTION.getMessage();
    }
}
