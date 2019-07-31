package com.incarcloud.sb2.config;

import com.alibaba.fastjson.JSON;
import com.incarcloud.sb2.security.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                /* CSRF */
                .and()
                .csrf().disable(); //关闭CSRF防护机制

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();

        // 定义登录处理接口
        filter.setFilterProcessesUrl("/api/plat/test/authLogin");
        //filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/plat/test/authLogin", "POST"));

        // 定义登录成功后处理器
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            // 返回json数据
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("code", "0000");
            returnData.put("message", "登录成功");
            returnData.put("data", authentication.getPrincipal());

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

            PrintWriter output = response.getWriter();
            output.write(JSON.toJSONString(returnData));
            output.flush();
            output.close();
        });

        // 定义登录失败后处理器
        filter.setAuthenticationFailureHandler((request, response, exception) -> {  //定义登录失败后处理器
            // 返回json数据
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("code", "0002");
            returnData.put("message", "用户名或密码错误");

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

            PrintWriter output = response.getWriter();
            output.write(JSON.toJSONString(returnData));
            output.flush();
            output.close();

        });

        filter.setAuthenticationManager(authenticationManagerBean());

        return filter;
    }
}
