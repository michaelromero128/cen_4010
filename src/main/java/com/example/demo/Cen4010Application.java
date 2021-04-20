package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
public class Cen4010Application {

	public static void main(String[] args) {
		SpringApplication.run(Cen4010Application.class, args);
	}
	
	@Bean 
	BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
