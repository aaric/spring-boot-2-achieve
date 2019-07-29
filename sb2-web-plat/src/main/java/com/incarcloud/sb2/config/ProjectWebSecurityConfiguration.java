package com.incarcloud.sb2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 项目Spring Security配置
 *
 * @author Aaric, created on 2019-07-29T11:35.
 * @since 0.6.0-SNAPSHOT
 */
@Configuration
public class ProjectWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()/*.loginPage("/login")*/ //定义登录页面
                .loginProcessingUrl("/api/plat/test/login") //定义登录处理接口
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**").permitAll() //设置所有人都可以访问在线文档
                .anyRequest() //登录后可用访问任何请求
                .authenticated()
                .and()
                .csrf().disable(); //关闭CSRF防护机制
    }
}
