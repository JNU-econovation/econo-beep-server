package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EconoIDPImpl implements EconoIDP {

    private final EconoIDPApi econoIDPApi;

    @Override
    public UserSaveDto getUserInfoDtoByAccessToken(String accessToken) {
//        UserIDPDto userIDPDto = econoIDPApi.getUserByAccessToken(accessToken);
//        return new UserSaveDto(userIDPDto);

        return UserSaveDto.builder()
                .idpId(999L)
                .year(21)
                .username("삡")
                .userEmail("beep@gmail.com")
                .build();
    }
}
