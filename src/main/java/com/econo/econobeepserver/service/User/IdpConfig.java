package com.econo.econobeepserver.service.User;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IdpConfig {

    public UserIdp userIdp() {
        return new UserEconoIdp();
    }
}
