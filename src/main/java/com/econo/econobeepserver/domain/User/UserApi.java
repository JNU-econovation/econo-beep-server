package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.dto.User.UserInfoDto;

public interface UserApi {

    UserInfoDto getUserInfoDtoByPinCode(String pinCode);
}
