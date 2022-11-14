package com.econo.econobeepserver.dto.User;

import com.econo.econobeepserver.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserInfoDto {

    private Long id;
    private String username;
    private Integer year;
    private String userEmail;

    @Builder
    public UserInfoDto(Long id, String username, Integer year, String userEmail) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.userEmail = userEmail;
    }

    public UserInfoDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.year = user.getYear();
        this.userEmail = user.getUserEmail();
    }
}
