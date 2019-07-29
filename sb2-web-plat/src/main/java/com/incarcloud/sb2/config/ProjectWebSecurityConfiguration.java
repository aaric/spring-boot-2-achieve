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
        /*http.formLogin().loginPage("/api/plat/test/authRedirect") //定义登录页面
                .loginProcessingUrl("/api/plat/test/authLogin") //定义登录处理接口
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**", "/api/plat/test/authRedirect").permitAll() //设置所有人都可以访问在线文档
                .anyRequest() //登录后可用访问任何请求
                .authenticated()
                .and()
                .csrf().disable(); //关闭CSRF防护机制*/

        http.authorizeRequests()
                /* 公共访问资源 */
                .antMatchers("/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**").permitAll() //设置所有人都可以访问在线文档
                .antMatchers("/api/plat/test/authLogin", "/api/plat/test/authRedirect").permitAll() // 设置不拦截登录地址
                .anyRequest()
                .authenticated()
                /* 登录 */
                .and()
                .formLogin()
                .loginPage("/api/plat/test/authRedirect") //定义登录页面
                .loginProcessingUrl("/api/plat/test/authLogin") //定义登录处理接口
                .usernameParameter("u") //定义用户名接收字段
                .passwordParameter("p") //定义密码接收字段
                .defaultSuccessUrl("/success") //定义登录成功后跳转地址
                .failureUrl("/failure")
                /* 会话管理 */
                .and()
                .sessionManagement()
                .maximumSessions(1) //同一个账号最大登录数量限制（只准许单点登录）
                /* 登出 */
                .and().and()
                .logout()
                .invalidateHttpSession(true) //设置会话失效
                .clearAuthentication(true) //清除认证信息
                /*.and()
                .httpBasic()*/
                .and()
                .csrf().disable(); //关闭CSRF防护机制
    }
}
