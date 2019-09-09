package io.sparrow.sb2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot启动类
 *
 * @author Aaric, created on 2019-06-21T18:00.
 * @since 0.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.incarcloud", "io.sparrow.sb2"})
public class App implements CommandLineRunner {

    /**
     * Main
     *
     * @param args 命令行传参
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * Init Runner
     *
     * @param args 命令行传参
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Add your initialization code here.");
    }
}
