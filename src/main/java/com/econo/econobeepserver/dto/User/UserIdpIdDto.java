package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserIdpIdDto {

    private Long id;
    private Integer year;
    private String username;
    private String email;
    private String profileImageUrl;

    @Builder
    public UserIdpIdDto(Long id, Integer year, String email, String username) {
        this.id = id;
        this.year = year;
        this.username = username;
        this.email = email;
        this.profileImageUrl = null;
    }
}
