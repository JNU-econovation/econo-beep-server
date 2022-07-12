package com.econo.econobeepserver.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    NOT_FOUND_RENTEE_EXCEPTION("요청한 비품(들)이 없습니다."),
    NOT_RENTER_EXCEPTION("해당 비품의 소유자가 아닙니다."),
    ALREADY_RENTED_EXCEPTION("이미 대여된 비풉입니다."),
    UNRENTABLE_EXCEPTION("대여할 수 없는 상태의 비품입니다.");

    public final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
