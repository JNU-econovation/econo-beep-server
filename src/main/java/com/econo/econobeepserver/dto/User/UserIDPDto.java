package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserIDPDto {

    private Long id;
    private Integer year;
    private String name;
    private String email;
    private String profileImageUrl;

    @Builder
    public UserIDPDto(Long id, Integer year, String name, String email, String profileImageUrl) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }
}
