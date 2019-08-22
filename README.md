# Spring Boot 2 Achieve
Spring Boot 2.x Learning.


## 一、里程碑
|序号|版本|规划清单|状态|备注|
|:-:|:--:|:-----|:--:|:--|
|1|`milestone-1.0`|基础集成与约定（**渗透测试+等保建议**）|开发中|基础框架|
|2|`milestone-2.0`|WEB网站**机构-用户-权限**基础功能|未来|增强版框架|
|3|`milestone-3.0`|通用**核心业务**功能|未来|微服务架构，JDK11+Gradle5.4+|
|4|`milestone-4.0`|云规划|未来|初代车联网云|


## 二、`milestone-1.0`版本规划与更新日志
1. [x] 集成**Swagger2**组件，指定`SwaggerBootstrapUi`风格(版本：0.2.2-SNAPSHOT)；
2. [x] 支持国际化，继承`AbstractWebMvcConfigurationSupport`配置（版本：0.3.2-SNAPSHOT）；
3. [x] 支持数据校验，使用`validation-api`注解（版本：0.4.2-SNAPSHOT）；
4. [x] 支持自动化的数据库版本管理，使用`Flyway`插件（版本：0.5.1-SNAPSHOT）；
5. [x] 集成**Spring Security**，实现简单授权控制-NoDB（0.6.3-SNAPSHOT）；
6. [x] 集成**JWT**，支持JSON Token验证（0.7.0-SNAPSHOT）；
7. [x] 提供可扩展的**FTP**文件上传服务（0.8.0-SNAPSHOT）；
8. [x] 提供可扩展的**Email**发送邮件服务（0.8.1-SNAPSHOT）；
9. [x] 提供可扩展的**SMS**发送验证码+短信通知服务（0.8.2-SNAPSHOT）；
10. [ ] 提供可扩展的**APP推送**（极光）服务（0.8.3-SNAPSHOT）；
11. [ ] 提供可扩展的**位置**(高德)IP定位+逆地理编码服务（0.8.4-SNAPSHOT）。


## 三、`milestone-2.0`版本规划
1. [ ] 完善可扩展日志与审计功能（1.1.0-SNAPSHOT）；
2. [ ] 完善**Jenkins Pipeline**流水线，实现自动化*CI/CD*（1.2.0-SNAPSHOT）；
3. [ ] 实现可扩展`机构-用户-权限`业务功能（1.3.0-SNAPSHOT）；
4. [ ] 实现可扩展`省-市-区`业务功能（1.4.0-SNAPSHOT）；
5. [ ] 实现可扩展`第三方支付`业务功能（1.5.0-SNAPSHOT）；
6. [ ] 集成`Activiti`工作流引擎（1.6.0-SNAPSHOT）。


## 四、`milestone-3.0`版本规划
1. [ ] 完成第一版Spring Cloud架构（2.1.0-SNAPSHOT）；
2. [ ] 实现可扩展`uid`服务（雪花算法）（2.2.0-SNAPSHOT）；
3. [ ] 融合大数据业务（待定）。


## 五、`milestone-4.0`版本规划
1. [ ] 完成第一版CAS单点登录平台（3.1.0-SNAPSHOT）；
2. [ ] 集成`Spring Social`实现第三方登录-QQ（3.2.0-SNAPSHOT）；
3. [ ] 集成`Spring Social`实现第三方登录-微信（3.3.0-SNAPSHOT）；
4. [ ] 实现支持**小程序**通用逻辑（3.4.0-SNAPSHOT）；
5. [ ] 规划初代云+`5G`娱乐（待定）。


## 六、其他说明
### 6.1 MySQL建库语句

> 注意: **MySQL 8.0** 以后，`charset`默认为`utf8mb4`非`latin1`，`collate`默认`utf8mb4_0900_ai_ci`。

```sql
/* utf8mb4: 解决存储emoji表情问题; utf8mb4_bin: 要求区分英文字母大小写 */
-- MySQL8已将创建用户和授权语句分离，必须分开写
sql> create database testdb default charset utf8mb4 collate utf8mb4_bin;
-- MySQL8默认是严格加密模式，修改为普通加密模式，并更新用户密码，建议更新Client，以下为非优雅的方式
-- sql> create user 'testdb'@'%' identified with mysql_native_password by 'testdb';
sql> create user 'testdb'@'%' identified by 'testdb';
sql> grant all privileges on testdb.* to 'testdb'@'%';
sql> flush privileges;
```

### 6.2 Flyway命名规范

> [Migrations - Flyway by Boxfuse • Database Migrations Made Easy.](https://flywaydb.org/documentation/migrations "Flyway Documentation Online")

![Flyway](https://github.com/aaric/spring-boot-2-achieve/raw/master/docs/images/gradle_flyway_naming.png "Flyway Naming Rule")


## 七、FAQ
1. **建议MySQL使用`utf8mb`字符集的理由**
    - 技术趋势。MySQL 8.0之后，已经将`utf8mb`设置为数据库默认字符集；
    - 关注手机输入法，支持*emoji*表情存储，显得非常必要。

2. **选择Security安全框架，弃用Shiro的理由**
    - *Spring Boot*对*Security*框架的约定配置，很大程度上减小*Security*框架的集成难度；
    - 以后支持*Spring Cloud*框架比较容易，使用*Apache Shiro*框架明显不合适了。
3. **Gradle配置文件implement替换compile的理由**
    - 加快编译速度；
    - 隐藏对外不必要的接口。


## 八、附录
1. **Spring Security配置参考**
```java
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* 公共访问资源 */
                .antMatchers(
                    "/swagger-resources",
                    "/v2/api-docs",
                    "/doc.html",
                    "/webjars/bycdao-ui/**")
                .permitAll() //设置所有人都可以访问在线文档
                .antMatchers(
                    "/api/auth/Login",
                    "/api/auth/current")
                .permitAll() // 设置不拦截登录地址
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
                    // 定义登录成功处理
                })
                .failureHandler((request, response, exception) -> {  //定义登录失败后处理器
                    // 定义登录失败处理

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
                    // 定义注销成功处理
                })
                .invalidateHttpSession(true) //设置会话失效
                .clearAuthentication(true) //清除认证信息
                /* 权限异常处理 */
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {  //定义访问失败后处理器
                    // 定义无权限访问处理
                })
                /* CSRF */
                .and()
                .csrf().disable(); //关闭CSRF防护机制
    }
}
```

2. **Jenkins Pipeline（非标准DSL）配置参考**
```groovy
node {
   stage('Preparation') {
        git branch: 'master', url: 'https://github.com/aaric/spring-boot-2-achieve'
   }

    stage('Build') {
        // no 'java' --> sudo ln -s /usr/java/jdk1.8.0_172/bin/java /usr/bin/java
        if (isUnix()) {
            sh "${tool 'gradle-5.2.1'}/bin/gradle clean build"
        }
   }

   stage('Test Reports') {
       junit '**/build/test-results/test/*.xml'
   }
}
```

3. **Docker使用MySQL8数据库**
```bash
~>$ sudo docker pull mysql:8.0.16
~>$ sudo docker run --name mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=yourpassword -d mysql:8.0.16
~>$ sudo docker exec -it mysql8 /bin/bash
```
