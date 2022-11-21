package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.dto.User.UserSaveDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idpId;

    @NotNull
    private Integer year;

    @NotNull
    private String username;

    @NotNull
    private String userEmail;

    private String profileImageUrl;

    @NotNull
    private Role role;

    @Builder
    public User(Long idpId, Integer year, String username, String userEmail, String profileImageUrl, Role role) {
        this.idpId = idpId;
        this.year = year;
        this.username = username;
        this.userEmail = userEmail;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }


    public void update(UserSaveDto userSaveDto) {
        this.idpId = userSaveDto.getIdpId();
        this.year = userSaveDto.getYear();
        this.username = userSaveDto.getUsername();
        this.userEmail = userSaveDto.getUserEmail();
        this.profileImageUrl = userSaveDto.getProfileImageUrl();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
