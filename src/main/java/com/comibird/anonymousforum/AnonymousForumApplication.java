package com.comibird.anonymousforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AnonymousForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonymousForumApplication.class, args);
    }

}
