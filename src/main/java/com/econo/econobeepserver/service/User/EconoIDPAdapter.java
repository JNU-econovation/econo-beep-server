package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;

public interface EconoIDPAdapter {

    UserIdpIdDto getUserIdpIdDtoByIdpId(Long idpId);
    UserIdpTokenDto getUserIdpTokenDtoByIdpToken(String idpToken);
}
