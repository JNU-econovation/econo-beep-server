package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserInfoDto {

    private Long id;
    private String userName;
    private String pinCode;

    @Builder
    public UserInfoDto(Long id, String userName, String pinCode) {
        this.id = id;
        this.userName = userName;
        this.pinCode = pinCode;
    }
}
