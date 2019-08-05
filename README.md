# Spring Boot 2 Achieve
Spring Boot 2.x Learning.


## 一、里程碑
|序号|版本|规划清单|状态|备注|
|:-:|:--:|:-----|:--:|:--|
|1|`milestone-1.0`|基础集成与约定（**渗透测试+等保建议**）|开发中|需要区分WEB端和APP端技术细节|
|2|`milestone-2.0`|WEB网站**机构-用户-权限**基础功能|未来|引入*Jenkins*进行*CI/CD*|
|3|`milestone-3.0`|通用**核心业务**功能|未来||


## 二、`milestone-1.0`更新日志
1. 集成Swagger2组件，指定`SwaggerBootstrapUi`风格(版本：0.2.2-SNAPSHOT)；
2. 支持国际化，继承`DefaultWebMvcConfigurationSupport`配置（版本：0.3.2-SNAPSHOT）；
3. 支持数据校验，使用`validation-api`注解（版本：0.4.2-SNAPSHOT）；
4. 支持自动化的数据库版本管理，使用`Flyway`插件（版本：0.5.1-SNAPSHOT）；
5. 集成Spring Security，实现简单授权控制-NoDB（0.6.1-SNAPSHOT）。


## 三、其他
### 3.1 MySQL建库语句

> 注意: **MySQL 8.0** 以后，`charset`默认为`utf8mb4`非`latin1`，`collate`默认`utf8mb4_0900_ai_ci`。

```sql
/* utf8mb4: 解决存储emoji表情问题; utf8mb4_bin: 要求区分英文字母大小写 */
sql> create database testdb default charset utf8mb4 collate utf8mb4_bin;
sql> grant all privileges on testdb.* to 'testdb'@'%' identified by 'testdb' with grant option;
sql> flush privileges;
```

### 3.2 Flyway命名规范

> [Migrations - Flyway by Boxfuse • Database Migrations Made Easy.](https://flywaydb.org/documentation/migrations "Flyway Documentation Online")

![Flyway](https://github.com/aaric/spring-boot-2-achieve/raw/master/flyway_naming.png "Flyway Naming Rule")


## 四、FAQ
1. **建议MySQL使用`utf8mb`字符集的理由**
    - 技术趋势。MySQL 8.0之后，已经将`utf8mb`设置为数据库默认字符集；
    - 关注手机输入法，支持*emoji*表情存储，显得非常必要。

2. **选择Security安全框架，弃用Shiro的理由**
    - *Spring Boot*对*Security*框架的约定配置，很大程度上减小*Security*框架的集成难度；
    - 以后支持*Spring Cloud*框架比较容易，使用*Apache Shiro*框架明显不合适了。


## 五、附录
1. **Spring Security配置参考**
```java
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* 公共访问资源 */
                .antMatchers("/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**").permitAll() //设置所有人都可以访问在线文档
                .antMatchers("/api/auth/Login", "/api/auth/current").permitAll() // 设置不拦截登录地址
                .anyRequest()
                .authenticated()
                /* 登录 */
                .and()
                .formLogin()
                .loginPage("/api/auth/current") //定义登录页面
                .loginProcessingUrl("/api/auth/login") //定义登录处理接口
                .usernameParameter("u") //定义用户名接收字段
                .passwordParameter("p") //定义密码接收字段
                .successHandler((request, response, authentication) -> { //定义登录成功后处理器
                    // 自定义登录成功处理
                })
                .failureHandler((request, response, exception) -> {  //定义登录失败后处理器
                    // 自定义登录失败处理

                })
                /* 会话管理 */
                .and()
                .sessionManagement()
                .maximumSessions(1) //同一个账号最大登录数量限制（只准许单点登录）
                /* 登出 */
                .and().and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler((request, response, authentication) -> {  //定义注销成功后处理器
                    // 自定义注销成功处理
                })
                .invalidateHttpSession(true) //设置会话失效
                .clearAuthentication(true) //清除认证信息
                /* 权限异常处理 */
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {  //定义访问失败后处理器
                    // 自定义无权限访问处理
                })
                /* CSRF */
                .and()
                .csrf().disable(); //关闭CSRF防护机制
    }
}
```