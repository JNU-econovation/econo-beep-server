package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEconoIdpTest {

    UserEconoIdp userEconoIdp;

    @Value("${ECONO_IDP_API}")
    private String ECONO_IDP_API;

    @Value("${ECONO_IDP_USER_EMAIL}")
    private String ECONO_IDP_USER_EMAIL;

    @Value("${ECONO_IDP_USER_PASSWORD}")
    private String ECONO_IDP_USER_PASSWORD;

    private String ECONO_IDP_API_TOKEN_REQUEST = ECONO_IDP_API + "/api/accounts/login/process";

    /*
    given idpId = 99L, idpToken
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

        URI uri = URI.create(ECONO_IDP_API_TOKEN_REQUEST + "?userEmail=" + ECONO_IDP_USER_EMAIL + "&password=" + ECONO_IDP_USER_PASSWORD + "&redirectUrl=naver.com");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<IdpTokenDto> response = restTemplate.getForEntity(uri, IdpTokenDto.class);
        String idpToken = response.getBody().getAccessToken();

        // when
        UserIdpIdDto userIdpIdDto = userEconoIdp.getUserIdpIdDtoByIdpId(idpId, idpToken);

        // then
        assertThat(userIdpIdDto.getId()).isEqualTo(idpId);
        assertThat(userIdpIdDto.getUsername()).isEqualTo("권순찬");
        assertThat(userIdpIdDto.getYear()).isEqualTo(21);
        assertThat(userIdpIdDto.getEmail()).isEqualTo("sckwon770@gmail.com");
    }

    /*
    given idpToken
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

class IdpTokenDto {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}