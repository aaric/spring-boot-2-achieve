package com.incarcloud.sb2;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Launcher.
 *
 * @author Aaric, created on 2019-06-21T18:00.
 * @since 0.2.0-SNAPSHOT
 */
@SpringBootApplication(scanBasePackages = Constant.DEFAULT_DOMAIN_PREFIX)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
