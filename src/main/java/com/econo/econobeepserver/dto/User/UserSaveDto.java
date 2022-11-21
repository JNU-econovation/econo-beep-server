package com.econo.econobeepserver.dto.User;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSaveDto {

    private Long idpId;
    private String username;
    private Integer year;
    private String userEmail;
    private String profileImageUrl;
    private Role role;

    @Builder
    public UserSaveDto(Long idpId, String username, Integer year, String userEmail, String profileImageUrl, Role role) {
        this.idpId = idpId;
        this.username = username;
        this.year = year;
        this.userEmail = userEmail;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

    public UserSaveDto(UserIDPDto userIDPDto) {
        this.idpId = userIDPDto.getId();
        this.username = userIDPDto.getUsername();
        this.year = userIDPDto.getYear();
        this.userEmail = userIDPDto.getUserEmail();
        this.profileImageUrl = userIDPDto.getProfileImageUrl();
        this.role = Role.ADMIN;
    }

    public User toEntity() {
        return User.builder()
                .idpId(idpId)
                .username(username)
                .year(year)
                .userEmail(userEmail)
                .profileImageUrl(profileImageUrl)
                .role(Role.USER)
                .build();
    }
}
