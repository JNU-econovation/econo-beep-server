package com.econo.econobeepserver.config;

import com.econo.econobeepserver.service.User.UserEconoIdp;
import com.econo.econobeepserver.service.User.UserFakerIdp;
import com.econo.econobeepserver.service.User.UserIdp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdpConfig {

    @Bean
    public UserIdp userIdp() {
        return new UserFakerIdp();
//        return new UserEconoIdp();
    }
}
