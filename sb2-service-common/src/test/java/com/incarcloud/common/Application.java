package com.incarcloud.common;

/**
 * Spring Boot Test Launcher.
 *
 * @author Aaric, created on 2019-08-15T17:56.
 * @since 0.8.0-SNAPSHOT
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
