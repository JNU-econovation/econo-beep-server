package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.dto.User.UserSaveDto;

public interface EconoIDP {

    UserSaveDto getUserInfoDtoByAccessToken(String accessToken);
}
