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
    private String username;
    private Integer year;
    private String email;
    private String profileImageUrl;

    @Builder
    public UserIdpIdDto(Long id, String username, Integer year, String email) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.email = email;
        this.profileImageUrl = null;
    }
}
