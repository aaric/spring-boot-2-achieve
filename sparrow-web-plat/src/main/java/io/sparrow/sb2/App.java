package io.sparrow.sb2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot启动类
 *
 * @author Aaric, created on 2019-06-21T18:00.
 * @version 0.2.0-SNAPSHOT
 */
@SpringBootApplication(scanBasePackages = {"com.incarcloud", "io.sparrow.sb2"})
@MapperScan({"com.incarcloud.**.mapper", "io.sparrow.sb2.**.mapper"})
public class App {

    /**
     * Main
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
