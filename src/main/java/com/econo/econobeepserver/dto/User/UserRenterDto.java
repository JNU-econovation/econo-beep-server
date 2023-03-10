package com.econo.econobeepserver.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserRenterDto {

    private Long id;
    private String username;
    private String profileImageUrl;

    @Builder
    public UserRenterDto(Long id, String username, String profileImageUrl) {
        this.id = id;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }

    public UserRenterDto(Long id, UserIdpIdDto userIdpIdDto) {
        this.id = id;
        this.username = userIdpIdDto.getUsername();
        this.profileImageUrl = userIdpIdDto.getProfileImageUrl();
    }
}
