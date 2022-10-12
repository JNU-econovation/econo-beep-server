package com.econo.econobeepserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EconoBeepServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconoBeepServerApplication.class, args);
	}

}
