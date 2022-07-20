package com.econo.econobeepserver.exception;

public class WrongFormatPinCodeException extends RuntimeException {
    public WrongFormatPinCodeException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.WRONG_FORMAT_PINCODE_EXCEPTION.getMessage();
    }
}
