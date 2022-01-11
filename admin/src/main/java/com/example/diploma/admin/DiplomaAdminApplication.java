package com.example.diploma.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.example.diploma.persistence"})
@EntityScan(basePackages = {"com.example.diploma.persistence"})
@ComponentScan(basePackages = {"com.example.diploma"})
public class DiplomaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaAdminApplication.class, args);
    }

}
