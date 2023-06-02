package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEconoIdpTest {

    UserEconoIdp userEconoIdp;

    @Value("${ECONO_IDP_API}")
    private String ECONO_IDP_API;

    @Value("${ECONO_IDP_USER_EMAIL}")
    private String ECONO_IDP_USER_EMAIL;

    @Value("${ECONO_IDP_USER_PASSWORD}")
    private String ECONO_IDP_USER_PASSWORD;

    /*
    given idpId = 99L
    when getUserIdpIdDtoByIdpId
    then return UserIdpIdDto {
        id = 99L,
        username = "권순찬",
        year = 21,
        email = "sckwon770@gmail.com"
    }
     */
    void test_getUserIdpIdDtoByIdpId_success() {
        // given
        Long idpId = 99L;

        // when
        UserIdpIdDto userIdpIdDto = userEconoIdp.getUserIdpIdDtoByIdpId(idpId);

        // then
        assertThat(userIdpIdDto.getId()).isEqualTo(idpId);
        assertThat(userIdpIdDto.getUsername()).isEqualTo("권순찬");
        assertThat(userIdpIdDto.getYear()).isEqualTo(21);
        assertThat(userIdpIdDto.getUserEmail()).isEqualTo("sckwon770@gmail.com");
    }

    /*
    given idpToken is return from {ECONO_IDP_API}/api/accounts/login/process?
    userEmail={ECONO_IDP_USER_EMAIL}&Password={ECONO_IDP_USER_PASSWORD}&redirectUrl=naver.com
    when getUserIdpTokenDtoByIdpToken
    then return UserIdpTokenDto {
        id = 2L,
        username = "경주",
        year = 22,
        userEmail = "joowoni99@gmail.com"
    }
     */
    void test_getUserIdpTokenDtoByIdpToken_success() {

    }
}
