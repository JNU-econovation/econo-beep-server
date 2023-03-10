package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;
import com.econo.econobeepserver.dto.User.UserProfileDto;
import com.econo.econobeepserver.dto.User.UserRenterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EconoIDPAdapterImpl implements EconoIDPAdapter {

    private final EconoIDP econoIDP;

    @Override
    public UserIdpIdDto getUserIdpIdDtoByIdpId(Long idpId) {
        return econoIDP.findIdpUserByIdpId(idpId);
    }

    @Override
    public UserIdpTokenDto getUserIdpTokenDtoByIdpToken(String idpToken) {
        return econoIDP.findIdpUserByIdpToken(idpToken);
    }
}
