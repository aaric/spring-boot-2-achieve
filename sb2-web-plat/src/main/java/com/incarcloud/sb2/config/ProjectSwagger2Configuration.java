package com.incarcloud.sb2.config;

import com.incarcloud.sb2.security.Swagger2LoginOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目Swagger2配置
 *
 * @author Aaric, created on 2019-06-28T13:59.
 * @since 0.2.1-SNAPSHOT
 */
@Configuration
@EnableSwagger2
public class ProjectSwagger2Configuration extends DefaultSwagger2ConfigurationSupport {

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

    /**
     * Spring Security登录与注销操作API
     *
     * @return
     */
    @Bean
    @Primary
    public ApiListingScanner addExtraOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
        return new Swagger2LoginOperations(apiDescriptionReader, apiModelReader, pluginsManager);
    }
}
