package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserProfileDto {

    private Long id;
    private String username;
    private Integer year;
    private String email;
    private String profileImageUrl;

    @Builder
    public UserProfileDto(Long id, String username, Integer year, String email, String profileImageUrl) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public UserProfileDto(Long id, UserIdpIdDto userIdpIdDto) {
        this.id = id;
        this.username = userIdpIdDto.getUsername();
        this.year = userIdpIdDto.getYear();
        this.email = userIdpIdDto.getUserEmail();
        this.profileImageUrl = userIdpIdDto.getProfileImageUrl();
    }
}
