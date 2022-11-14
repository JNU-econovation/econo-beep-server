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
    private Integer year;
    private String username;
    private String userEmail;
    private Role role;

    @Builder
    public UserSaveDto(Long idpId, Integer year, String username, String userEmail, Role role) {
        this.idpId = idpId;
        this.year = year;
        this.username = username;
        this.userEmail = userEmail;
        this.role = role;
    }

    public UserSaveDto(UserIDPDto userIDPDto) {
        this.idpId = userIDPDto.getId();
        this.year = userIDPDto.getYear();
        this.username = userIDPDto.getUsername();
        this.userEmail = userIDPDto.getUserEmail();
        this.role = Role.ADMIN;
    }

    public User toEntity() {
        return User.builder()
                .idpId(idpId)
                .year(year)
                .username(username)
                .userEmail(userEmail)
                .role(Role.USER)
                .build();
    }
}
