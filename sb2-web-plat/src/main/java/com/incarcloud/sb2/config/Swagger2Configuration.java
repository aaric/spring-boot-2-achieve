package com.incarcloud.sb2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author Aaric, created on 2019-06-28T13:59.
 * @since 0.2.1-SNAPSHOT
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration extends DefaultSwagger2ConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(globalOperationParameters())
                /*.groupName("v1")*/
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.incarcloud.sb2"))
                .paths(PathSelectors.regex("/api/plat/.*"))
                .build();
    }
}
