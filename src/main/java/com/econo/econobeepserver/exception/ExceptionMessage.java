package com.econo.econobeepserver.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    NOT_FOUND_RENTEE_EXCEPTION("NOT_FOUND_RENTEE_EXCEPTION : 요청한 비품(들)이 없습니다."),
    NOT_RENTER_EXCEPTION("NOT_RENTER_EXCEPTION : 해당 비품의 소유자가 아닙니다."),
    ALREADY_RENTED_EXCEPTION("ALREADY_RENTED_EXCEPTION : 이미 대여된 비풉입니다."),
    UNRENTABLE_EXCEPTION("UNRENTABLE_EXCEPTION : 대여할 수 없는 상태의 비품입니다."),

    WRONG_FORMAT_PINCODE_EXCEPTION("WRONG_FORMAT_PINCODE_EXCEPTION : 잘못된 형태의 핀코드입니다."),
    NOT_FOUND_PINCODE_EXCEPTION("NOT_FOUND_PINCODE_EXCEPTION : 존재하지 않는 핀코드입니다.");

    public final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
