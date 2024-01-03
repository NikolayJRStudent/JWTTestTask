package com.example.jwttesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class JwtTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtTestTaskApplication.class, args);
    }

}
