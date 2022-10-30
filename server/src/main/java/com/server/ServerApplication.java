package com.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


//@Slf4j

@EntityScan("com.server.entity")     //Scan entity class
@EnableJpaRepositories("com.server.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class},scanBasePackages="com.server")
@CrossOrigin
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
