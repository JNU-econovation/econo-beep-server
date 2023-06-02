package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;

public class UserFakerIdp implements UserIdp {

    @Override
    public UserIdpIdDto getUserIdpIdDtoByIdpId(Long idpId) {
        return UserIdpIdDto.builder()
                .id(99L)
                .year(21)
                .username("권순찬")
                .userEmail("sckwon770@gmail.com")
                .build();
    }

    @Override
    public UserIdpTokenDto getUserIdpTokenDtoByIdpToken(String idpToken) {
        return UserIdpTokenDto.builder()
                .id(99L)
                .year(21)
                .username("권순찬")
                .userEmail("sckwon770@gmail.com")
                .build();
    }
}
