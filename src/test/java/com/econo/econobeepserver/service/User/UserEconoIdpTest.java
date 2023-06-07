package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.config.IdpConfig;
import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class UserEconoIdpTest {

    UserEconoIdp userEconoIdp = new UserEconoIdp();

    @Value("${ECONO_IDP_API}")
    private String ECONO_IDP_API;

    @Value("${ECONO_IDP_DEBUG_USER_EMAIL}")
    private String ECONO_IDP_USER_EMAIL;

    @Value("${ECONO_IDP_DEBUG_PASSWORD}")
    private String ECONO_IDP_USER_PASSWORD;
//
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userEconoIdp, "ECONO_IDP_API", ECONO_IDP_API);

    }


    /*
    given idpId = 99L
    when getUserIdpIdDtoByIdpId
    then return UserIdpTokenDto {
        id = 2L,
        username = "경주",
        year = 22,
        userEmail = "joowoni99@gmail.com"
    }
     */
    @Test
    void test_getUserIdpIdDtoByIdpId_success() {
        // given
        Long idpId = 2L;

        // when
        UserIdpIdDto userIdpIdDto = userEconoIdp.getUserIdpIdDtoByIdpId(idpId);

        // then
        assertThat(userIdpIdDto.getId()).isEqualTo(idpId);
        assertThat(userIdpIdDto.getUsername()).isEqualTo("경주");
        assertThat(userIdpIdDto.getYear()).isEqualTo(22);
        assertThat(userIdpIdDto.getUserEmail()).isEqualTo("joowoni99@gmail.com");
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
