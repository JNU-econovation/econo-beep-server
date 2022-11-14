package com.econo.econobeepserver.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    NOT_FOUND_RENTEE_EXCEPTION("NOT_FOUND_RENTEE_EXCEPTION: 일치하는 비품(들)이 없습니다."),
    NOT_FOUND_USER_EXCEPTION("NOT_FOUND_USER_EXCEPTION: 일치하는 DB에 유저가 없습니다."),
    NOT_RENTER_EXCEPTION("NOT_RENTER_EXCEPTION: 해당 비품의 소유자가 아닙니다."),
    ALREADY_RENTED_EXCEPTION("ALREADY_RENTED_EXCEPTION: 이미 대여된 비풉입니다."),
    UNRENTABLE_EXCEPTION("UNRENTABLE_EXCEPTION: 대여할 수 없는 상태의 비품입니다."),
    UNRETURNABLE_EXCEPTION("UNRETURNABLE_EXCEPTION: 반납할 수 없는 상태의 비품입니다."),

    WRONG_FORMAT_PINCODE_EXCEPTION("WRONG_FORMAT_PINCODE_EXCEPTION: 잘못된 형태의 핀코드입니다."),
    WRONG_ACCESS_TOKEN_EXCEPTION("WRONG_ACCESS_TOKEN_EXCEPTION: 잘못된 AccessToken 입니다."),

    IMAGE_IO_EXCEPTION("IMAGE_IO_EXCEPTION: ");

    public final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
