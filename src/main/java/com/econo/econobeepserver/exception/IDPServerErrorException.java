package com.econo.econobeepserver.exception;

public class IDPServerErrorException extends RuntimeException {
    public IDPServerErrorException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.IDP_SERVER_ERROR_EXCEPTION.getMessage();
    }
}
