package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;

public interface UserIdp {

    UserIdpIdDto getUserIdpIdDtoByIdpId(Long idpId);
    UserIdpTokenDto getUserIdpTokenDtoByIdpToken(String idpToken);
}
