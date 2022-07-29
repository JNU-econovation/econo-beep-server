package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotFoundPinCodeException;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    @Value("${TECONO_API_URL}")
    private String TECONO_API_URL;


    @Override
    public UserInfoDto getUserInfoDtoByPinCode(String pinCode) {
//        return WebClient.create(TECONO_API_URL + "user/pinCode/" + pinCode)
//                .get()
//                .retrieve()
//                .onStatus(
//                        HttpStatus.INTERNAL_SERVER_ERROR::equals,
//                        (response) -> {
//                            throw new NotFoundPinCodeException();
//                        })
//                .bodyToFlux(UserInfoDto.class)
//                .blockFirst();

        return UserInfoDto.builder()
                .id(999L)
                .userName("삡")
                .pinCode("3677")
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
