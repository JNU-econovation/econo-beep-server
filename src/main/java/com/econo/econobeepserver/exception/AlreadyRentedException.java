package com.econo.econobeepserver.exception;

public class AlreadyRentedException extends Exception{
    public AlreadyRentedException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.ALREADY_RENTED_EXCEPTION.getMessage();
    }
}
