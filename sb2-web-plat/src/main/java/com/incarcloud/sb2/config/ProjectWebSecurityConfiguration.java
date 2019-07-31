package com.incarcloud.sb2.config;

import com.alibaba.fastjson.JSON;
import com.incarcloud.sb2.security.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                .antMatchers("/api/plat/test/authLogin", "/api/plat/test/authRedirect").permitAll() // 设置不拦截登录地址
                .anyRequest()
                .authenticated()
                /* 登录 */
                .and()
                .formLogin()
                .loginPage("/api/plat/test/authRedirect") //定义登录页面
                /* 会话管理 */
                .and()
                .sessionManagement()
                .maximumSessions(1) //同一个账号最大登录数量限制（设置为1相当于单点登录）
                /* 登出 */
                .and().and()
                .logout()
                .logoutUrl("/api/user/Logout")
                .logoutSuccessHandler(this::logoutSuccessHandler)
                .invalidateHttpSession(true) //设置会话失效
                .clearAuthentication(true) //清除认证信息
                /* 权限异常处理 */
                .and()
                .exceptionHandling()
                .accessDeniedHandler(this::accessDeniedHandler)
                /* CSRF */
                .and()
                .csrf().disable(); //关闭CSRF防护机制

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();

        // 设置登录处理接口地址
        filter.setFilterProcessesUrl("/api/plat/test/authLogin");
        //filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/plat/test/authLogin", "POST"));

        // 设置登录成功后处理器
        filter.setAuthenticationSuccessHandler(this::loginSuccessHandler);

        // 设置登录失败后处理器
        filter.setAuthenticationFailureHandler(this::loginFailureHandler);

        // 设置认证管理器
        filter.setAuthenticationManager(authenticationManagerBean());

        return filter;
    }

    /**
     * 定义登录成功后处理器
     *
     * @throws IOException
     */
    private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", "登录成功");
        returnData.put("data", authentication.getPrincipal());

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        PrintWriter output = response.getWriter();
        output.write(JSON.toJSONString(returnData));
        output.flush();
        output.close();
    }

    /**
     * 定义登录失败后处理器
     *
     * @throws IOException
     */
    private void loginFailureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0002");
        returnData.put("message", "用户名或密码错误");

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        PrintWriter output = response.getWriter();
        output.write(JSON.toJSONString(returnData));
        output.flush();
        output.close();
    }

    /**
     * 定义注销成功后处理器
     *
     * @throws IOException
     * @throws ServletException
     */
    private void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0000");
        returnData.put("message", "注销成功");

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        PrintWriter output = response.getWriter();
        output.write(JSON.toJSONString(returnData));
        output.flush();
        output.close();
    }

    /**
     * 定义访问失败后处理器
     *
     * @throws IOException
     */
    private void accessDeniedHandler(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("code", "0003");
        returnData.put("message", "权限不足，禁止访问");

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        PrintWriter output = response.getWriter();
        output.write(JSON.toJSONString(returnData));
        output.flush();
        output.close();
    }
}
