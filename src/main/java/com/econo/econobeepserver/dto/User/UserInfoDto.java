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
    private String username;
    private Integer year;
    private String email;

    @Builder
    public UserInfoDto(Long id, String username, Integer year, String email) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.email = email;
    }
}
