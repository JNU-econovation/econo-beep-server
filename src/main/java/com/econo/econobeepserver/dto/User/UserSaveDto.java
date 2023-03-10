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
    private Role role;

    @Builder
    public UserSaveDto(Long idpId) {
        this.idpId = idpId;
        this.role = Role.USER;
    }

    public User toEntity() {
        return User.builder()
                .idpId(idpId)
                .role(role)
                .build();
    }
}
