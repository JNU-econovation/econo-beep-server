package com.econo.econobeepserver.domain.User;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private Role role;

    @Builder
    public User(Long idpId, Integer year, String username, String userEmail, Role role) {
        this.idpId = idpId;
        this.year = year;
        this.username = username;
        this.userEmail = userEmail;
        this.role = role;
    }
}
