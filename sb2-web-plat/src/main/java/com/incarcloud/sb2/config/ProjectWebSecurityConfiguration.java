package com.incarcloud.sb2.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目Spring Security配置
 *
 * @author Aaric, created on 2019-07-29T11:35.
 * @since 0.6.0-SNAPSHOT
 */
@Configuration
public class ProjectWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$5PkWw0ceheJxbLt0.RbAke609QgcB0SEOxjJG1XD5f88A8cC1slZa").roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* 公共访问资源 */
                .antMatchers("/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**").permitAll() //设置所有人都可以访问在线文档
                .antMatchers("/api/plat/test/authLogin", "/api/plat/test/authRedirect", "/api/plat/test/authLoginFailure").permitAll() // 设置不拦截登录地址
                .anyRequest()
                .authenticated()
                /* 登录 */
                .and()
                .formLogin()
                .loginPage("/api/plat/test/authRedirect") //定义登录页面
                .loginProcessingUrl("/api/plat/test/authLogin") //定义登录处理接口
                .usernameParameter("u") //定义用户名接收字段
                .passwordParameter("p") //定义密码接收字段
                .successHandler((request, response, authentication) -> { //定义登录成功后处理器
                    // 返回json数据
                    Map<String, Object> returnData = new HashMap<>();
                    returnData.put("code", "0000");
                    returnData.put("message", "登录成功");
                    returnData.put("data", authentication.getPrincipal());

                    response.setContentType("application/json;charset=utf-8");

                    PrintWriter output = response.getWriter();
                    output.write(JSON.toJSONString(returnData));
                    output.flush();
                    output.close();

                })
                .failureHandler((request, response, exception) -> {  //定义登录失败后处理器
                    // 返回json数据
                    Map<String, Object> returnData = new HashMap<>();
                    returnData.put("code", "0002");
                    returnData.put("message", "用户名或密码错误");

                    response.setContentType("application/json;charset=utf-8");

                    PrintWriter output = response.getWriter();
                    output.write(JSON.toJSONString(returnData));
                    output.flush();
                    output.close();

                })
                /* 会话管理 */
                .and()
                .sessionManagement()
                .maximumSessions(1) //同一个账号最大登录数量限制（只准许单点登录）
                /* 登出 */
                .and().and()
                .logout()
                .logoutUrl("/api/plat/test/authLogout")
                .logoutSuccessHandler((request, response, authentication) -> {  //定义注销成功后处理器
                    // 返回json数据
                    Map<String, Object> returnData = new HashMap<>();
                    returnData.put("code", "0000");
                    returnData.put("message", "注销成功");

                    response.setContentType("application/json;charset=utf-8");

                    PrintWriter output = response.getWriter();
                    output.write(JSON.toJSONString(returnData));
                    output.flush();
                    output.close();

                })
                .invalidateHttpSession(true) //设置会话失效
                .clearAuthentication(true) //清除认证信息
                /* 权限异常处理 */
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {  //定义访问失败后处理器
                    // 返回json数据
                    Map<String, Object> returnData = new HashMap<>();
                    returnData.put("code", "0003");
                    returnData.put("message", "权限不足，禁止访问");

                    response.setContentType("application/json;charset=utf-8");

                    PrintWriter output = response.getWriter();
                    output.write(JSON.toJSONString(returnData));
                    output.flush();
                    output.close();

                })
                /* CSRF */
                .and()
                .csrf().disable(); //关闭CSRF防护机制
    }
}
