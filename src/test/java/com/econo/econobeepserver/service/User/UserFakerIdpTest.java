package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFakerIdpTest {

    private UserFakerIdp userFakerIdp = new UserFakerIdp();

    @Test
    @DisplayName("getUserIdpIdDtoByIdpId()가 정해진대로 Fake Value를 반환하는지 확인")
    void test_getUserIdpIdDtoByIdpId_success() {
        // given
        Long idpId = 1234L;

        // when
        UserIdpIdDto userIdpIdDto = userFakerIdp.getUserIdpIdDtoByIdpId(idpId);

        // then
        assertThat(userIdpIdDto.getId()).isEqualTo(UserFakerIdp.FAKE_USER_ID);
        assertThat(userIdpIdDto.getUsername()).isEqualTo(UserFakerIdp.FAKE_USER_NAME);
        assertThat(userIdpIdDto.getYear()).isEqualTo(UserFakerIdp.FAKE_USER_YEAR);
        assertThat(userIdpIdDto.getUserEmail()).isEqualTo(UserFakerIdp.FAKE_USER_EMAIL);
    }

    @Test
    @DisplayName("getUserIdpIdDtoByIdpToken()가 정해진대로 Fake Value를 반환하는지 확인")
    void test_getUserIdpTokenByIdpToken_success() {
        // given
        String anyToken = "anyToken";

        // when
        UserIdpTokenDto userIdpTokenDto = userFakerIdp.getUserIdpTokenDtoByIdpToken(anyToken);

        // then
        assertThat(userIdpTokenDto.getId()).isEqualTo(UserFakerIdp.FAKE_USER_ID);
        assertThat(userIdpTokenDto.getUsername()).isEqualTo(UserFakerIdp.FAKE_USER_NAME);
        assertThat(userIdpTokenDto.getYear()).isEqualTo(UserFakerIdp.FAKE_USER_YEAR);
        assertThat(userIdpTokenDto.getUserEmail()).isEqualTo(UserFakerIdp.FAKE_USER_EMAIL);
    }
}
