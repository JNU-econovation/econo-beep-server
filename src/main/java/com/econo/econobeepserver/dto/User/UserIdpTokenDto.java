package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserIdpTokenDto {

    private Long id;
    private String username;
    private Integer year;
    private String userEmail;
    private String profileImageUrl;

    @Builder
    public UserIdpTokenDto(Long id, String username, Integer year, String userEmail) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.userEmail = userEmail;
        this.profileImageUrl = null;
    }
}
