package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    @Override
    public UserInfoDto getUserInfoDtoByPinCode(String pinCode) {
        return UserInfoDto.builder()
                .uid(1L)
                .name("tester")
                .pinCode("1234")
                .build();
    }
}
