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
    private String username;
    private String userEmail;
    private String profileImageUrl;

    @Builder
    public UserIDPDto(Long id, Integer year, String username, String userEmail, String profileImageUrl) {
        this.id = id;
        this.year = year;
        this.username = username;
        this.userEmail = userEmail;
        this.profileImageUrl = profileImageUrl;
    }
}
