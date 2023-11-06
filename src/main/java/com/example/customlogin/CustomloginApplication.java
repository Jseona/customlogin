package com.example.customlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomloginApplication.class, args);
    }

}
