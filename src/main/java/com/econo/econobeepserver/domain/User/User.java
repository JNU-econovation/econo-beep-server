package com.econo.econobeepserver.domain.User;

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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long idpId, Role role) {
        this.idpId = idpId;
        this.role = role;
    }
}
