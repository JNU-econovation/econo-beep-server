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
    private String name;
    private Integer year;
    private String email;
    private String profileImageUrl;
    private Role role;

    @Builder
    public UserSaveDto(Long idpId, String name, Integer year, String email, String profileImageUrl, Role role) {
        this.idpId = idpId;
        this.name = name;
        this.year = year;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

    public UserSaveDto(UserIDPDto userIDPDto) {
        this.idpId = userIDPDto.getId();
        this.name = userIDPDto.getName();
        this.year = userIDPDto.getYear();
        this.email = userIDPDto.getEmail();
        this.profileImageUrl = userIDPDto.getProfileImageUrl();
        this.role = Role.ADMIN;
    }

    public User toEntity() {
        return User.builder()
                .idpId(idpId)
                .name(name)
                .year(year)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .role(Role.USER)
                .build();
    }
}
