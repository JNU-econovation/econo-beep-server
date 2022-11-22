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
    private String name;
    private Integer year;
    private String email;
    private String profileImageUrl;

    @Builder
    public UserProfileDto(Long id, String name, Integer year, String email, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public UserProfileDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.year = user.getYear();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
