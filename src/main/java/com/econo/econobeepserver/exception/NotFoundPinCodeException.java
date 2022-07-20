package com.econo.econobeepserver.exception;

public class NotFoundPinCodeException extends RuntimeException {
    public NotFoundPinCodeException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.NOT_FOUND_PINCODE_EXCEPTION.getMessage();
    }
}
