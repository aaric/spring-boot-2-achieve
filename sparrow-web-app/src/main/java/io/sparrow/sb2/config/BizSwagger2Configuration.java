package io.sparrow.sb2.config;

import com.incarcloud.mvc.config.AbstractSwagger2ConfigurationSupport;
import com.incarcloud.mvc.token.settings.AuthJwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目Swagger2配置
 *
 * @author Aaric, created on 2019-06-28T13:59.
 * @since 0.2.1-SNAPSHOT
 */
@Configuration
@EnableSwagger2
public class BizSwagger2Configuration extends AbstractSwagger2ConfigurationSupport {

    @Autowired
    protected AuthJwtProperties authJwtProperties;

    /**
     * 注册JWT验证参数信息
     *
     * @return
     */
    private List<Parameter> tokenOperationParameters() {
        List<Parameter> parameters = new ArrayList<>();

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(authJwtProperties.getCidHeaderName())
                .description("客户端ID")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("")
                .required(false);
        parameters.add(parameterBuilder.build());

        parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(authJwtProperties.getTokenHeaderName())
                .description("授权字符串Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("")
                .required(false);
        parameters.add(parameterBuilder.build());

        parameters.addAll(globalOperationParameters());

        return parameters;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(tokenOperationParameters())
                /*.groupName("v1")*/
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.sparrow.sb2"))
                .paths(PathSelectors.regex("/api/app/.*"))
                .build();
    }
}
