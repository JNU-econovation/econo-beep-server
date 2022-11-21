package com.econo.econobeepserver.exception;

public class ForbiddenRoleException extends RuntimeException{
    public ForbiddenRoleException() {
    }

    @Override
    public String getMessage() {
        return ExceptionMessage.FORBIDDEN_ROLE_EXCEPTION.getMessage();
    }
}
