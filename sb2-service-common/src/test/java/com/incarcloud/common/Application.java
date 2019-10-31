package com.incarcloud.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Test Launcher.
 *
 * @author Aaric, created on 2019-08-15T17:56.
 * @version 0.8.0-SNAPSHOT
 */
@SpringBootApplication
public class Application {

    /**
     * Main
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
