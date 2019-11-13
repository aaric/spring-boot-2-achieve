package com.incarcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Launcher.
 *
 * @author Aaric, created on 2019-11-11T17:47.
 * @version 1.3.0-SNAPSHOT
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
