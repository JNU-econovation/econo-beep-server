package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIDPDto;
import com.econo.econobeepserver.exception.ExpiredAccessTokenException;
import com.econo.econobeepserver.exception.WrongAccessTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class EconoIDPApi {

    @Value("${ECONO_IDP_API}")
    private String ECONO_IDP_API;

//    public UserIDPDto getUserByAccessToken(String accessToken) {
//        return WebClient.create(ECONO_IDP_API + "/usernames&accessToken=" + accessToken)
//                .get()
//                .retrieve()
//                .onStatus(
//                        HttpStatus.BAD_REQUEST::equals,
//                        (response) -> {
//                            throw new WrongAccessTokenException();
//                        })
//                .onStatus(
//                        HttpStatus.UNAUTHORIZED::equals,
//                        (response) -> {
//                            throw new ExpiredAccessTokenException();
//                        }
//                )
//                .bodyToFlux(UserIDPDto.class)
//                .blockFirst();
//    }
}
