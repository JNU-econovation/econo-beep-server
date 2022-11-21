package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserSaveDto;

public interface EconoIDP {

    UserSaveDto getUserSaveDtoByAccessToken(String accessToken);

}
