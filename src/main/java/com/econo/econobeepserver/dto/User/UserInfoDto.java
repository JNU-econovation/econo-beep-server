package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserInfoDto {

    private Long uid;
    private String name;
    private String pinCode;

    @Builder
    public UserInfoDto(Long uid, String name, String pinCode) {
        this.uid = uid;
        this.name = name;
        this.pinCode = pinCode;
    }
}
