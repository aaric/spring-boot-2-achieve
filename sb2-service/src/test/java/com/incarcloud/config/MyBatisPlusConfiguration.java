package com.incarcloud.config;

import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置
 *
 * @author Aaric, created on 2019-11-13T09:30.
 * @version 1.3.1-SNAPSHOT
 */
@Configuration
@MapperScan({"com.incarcloud.**.mapper"})
public class MyBatisPlusConfiguration {

    /**
     * PostgreSQL主键策略
     *
     * @return
     */
    @Bean
    public PostgreKeyGenerator postgreKeyGenerator() {
        return new PostgreKeyGenerator();
    }
}
