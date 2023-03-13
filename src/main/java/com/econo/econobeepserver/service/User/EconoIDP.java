package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;
import com.econo.econobeepserver.exception.IDPServerErrorException;
import com.econo.econobeepserver.exception.WrongAccessTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class EconoIDP {

    @Value("${ECONO_IDP_API}")
    private String ECONO_IDP_API;


    UserIdpIdDto findIdpUserByIdpId(Long idpId) throws WrongAccessTokenException, IDPServerErrorException {
        URI uri = UriComponentsBuilder
                .fromUriString(ECONO_IDP_API)
                .path("/api/users/{userId}")
                .encode()
                .build()
                .expand(idpId.toString())
                .toUri();

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserIdpIdDto> response = restTemplate.getForEntity(uri, UserIdpIdDto.class);

            return response.getBody();
        } catch (HttpServerErrorException.InternalServerError exception5xx) {
            throw new WrongAccessTokenException();
        } catch (HttpServerErrorException exception4xx) {
            throw new IDPServerErrorException();
        }
    }

    UserIdpTokenDto findIdpUserByIdpToken(String idpToken) throws WrongAccessTokenException, IDPServerErrorException {
        URI uri = UriComponentsBuilder
                .fromUriString(ECONO_IDP_API)
                .path("/api/users/token")
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(idpToken);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserIdpTokenDto> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, UserIdpTokenDto.class);

            return response.getBody();
        } catch (HttpServerErrorException.InternalServerError exception5xx) {
            throw new WrongAccessTokenException();
        } catch (HttpServerErrorException exception4xx) {
            throw new IDPServerErrorException();
        }
    }
}
