package com.incarcloud.sb2.config;

import com.incarcloud.sb2.config.settings.DocumentApiProperties;
import com.incarcloud.sb2.config.settings.DocumentAuthorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2默认配置
 *
 * @author Aaric, created on 2019-06-27T17:27.
 * @since 0.2.1-SNAPSHOT
 */
public abstract class Swagger2ConfigurationSupport {

    @Autowired
    protected DocumentApiProperties documentApiProperties;

    @Autowired
    protected DocumentAuthorProperties documentAuthorProperties;

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(documentApiProperties.getTitle())
                .description(documentApiProperties.getDescription())
                .termsOfServiceUrl(documentApiProperties.getServiceUrl())
                .version(documentApiProperties.getVersion())
                .contact(new Contact(documentAuthorProperties.getName(), documentAuthorProperties.getWebsiteUrl(), documentAuthorProperties.getEmail()))
                .build();
    }

    protected List<Parameter> globalOperationParameters() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();

        parameterBuilder.name(LocaleChangeInterceptor.DEFAULT_PARAM_NAME)
                .description("地区语言代码，默认zh_CN(简体中文), en_US(英文)")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .defaultValue("zh_CN")
                .required(false);

        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parameterBuilder.build());
        return parameters;
    }
}
