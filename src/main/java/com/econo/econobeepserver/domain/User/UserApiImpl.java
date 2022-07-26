package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    // TODO : Tecono 서버로부터 핀코드 유효성 확인
    // TODO : 유효하지 않은 핀코드면, 예외 던지기, 204 코드로
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
