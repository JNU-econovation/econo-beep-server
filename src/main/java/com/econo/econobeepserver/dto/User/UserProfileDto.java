package com.econo.econobeepserver.dto.User;

import com.econo.econobeepserver.domain.User.User;
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
    private String userEmail;
    private String profileImageUrl;

    @Builder
    public UserProfileDto(Long id, String username, Integer year, String userEmail, String profileImageUrl) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.userEmail = userEmail;
        this.profileImageUrl = profileImageUrl;
    }

    public UserProfileDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.year = user.getYear();
        this.userEmail = user.getUserEmail();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
