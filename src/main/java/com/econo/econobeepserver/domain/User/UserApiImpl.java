package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    @Override
    public UserInfoDto getUserInfoDtoByPinCode(String pinCode) {
        return UserInfoDto.builder()
                .uid(1L)
                .name("권순찬")
                .pinCode("1234")
                .build();
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void validatePinCode(String pinCode) {
        if (
                pinCode.length() != 4 || !isNumeric(pinCode)
        ) {
            throw new WrongFormatPinCodeException();
        }
    }
}
