package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;

public class UserFakerIdp implements UserIdp {

    public static final Long FAKE_USER_ID = 99L;
    public static final Integer FAKE_USER_YEAR = 22;
    public static final String FAKE_USER_NAME = "경주원";
    public static final String FAKE_USER_EMAIL = "joowoni99@gmail.com";

    @Override
    public UserIdpIdDto getUserIdpIdDtoByIdpId(Long idpId) {
        return UserIdpIdDto.builder()
                .id(FAKE_USER_ID)
                .year(FAKE_USER_YEAR)
                .username(FAKE_USER_NAME)
                .userEmail(FAKE_USER_EMAIL)
                .build();
    }

    @Override
    public UserIdpTokenDto getUserIdpTokenDtoByIdpToken(String idpToken) {
        return UserIdpTokenDto.builder()
                .id(FAKE_USER_ID)
                .year(FAKE_USER_YEAR)
                .username(FAKE_USER_NAME)
                .userEmail(FAKE_USER_EMAIL)
                .build();
    }
}
