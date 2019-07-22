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
4. 支持自动化的数据库版本管理，使用`Flyway`插件（版本：0.5.1-SNAPSHOT）。


## 三、其他
### 3.1 使用数据库

#### 3.1.1 MySQL建库语句

> 注意: **MySQL 8.0** 以后，`charset`默认为`utf8mb4`非`latin1`，`collate`默认`utf8mb4_0900_ai_ci`。

```sql
/* utf8mb4: 解决存储emoji表情问题; utf8mb4_bin: 要求区分英文字母大小写 */
sql> create database testdb default charset utf8mb4 collate utf8mb4_bin;
sql> grant all privileges on testdb.* to 'testdb'@'%' identified by 'testdb' with grant option;
sql> flush privileges;
```

#### 3.1.2 Flyway命名规范

> [Migrations - Flyway by Boxfuse • Database Migrations Made Easy.](https://flywaydb.org/documentation/migrations "Flyway Documentation Online")

![Flyway](.\flyway_naming.png "Flyway Naming Rule")

