package com.econo.econobeepserver.service.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdpConfig {

    public UserIdp userIdp() {
//        return new UserFakerIdp();
        return new UserEconoIdp();
    }
}
