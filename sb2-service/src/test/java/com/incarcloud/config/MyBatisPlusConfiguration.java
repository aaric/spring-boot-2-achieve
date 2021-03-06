package com.incarcloud.config;

import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis Plus配置
 *
 * @author Aaric, created on 2019-11-13T09:30.
 * @version 1.3.1-SNAPSHOT
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.incarcloud.*.mapper"})
public class MyBatisPlusConfiguration {

    /**
     * 设置PostgreSQL主键策略
     *
     * @return
     */
    @Bean
    public PostgreKeyGenerator postgreKeyGenerator() {
        return new PostgreKeyGenerator();
    }

    /**
     * 设置分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
