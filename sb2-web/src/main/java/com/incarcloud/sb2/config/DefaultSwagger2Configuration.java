package com.incarcloud.sb2.config;

import org.springframework.beans.factory.annotation.Value;
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
public abstract class DefaultSwagger2Configuration {

    @Value("${incar.document.api.title}")
    private String apiDocTitle;

    @Value("${incar.document.api.description}")
    private String apiDocDescription;

    @Value("${incar.document.api.service-url}")
    private String apiDocServiceUrl;

    @Value("${incar.document.api.version}")
    private String apiDocVersion;

    @Value("${incar.document.author.name}")
    private String authorName;

    @Value("${incar.document.author.website-url}")
    private String authorWebsiteUrl;

    @Value("${incar.document.author.email}")
    private String authorEmail;

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiDocTitle)
                .description(apiDocDescription)
                .termsOfServiceUrl(apiDocServiceUrl)
                .version(apiDocVersion)
                .contact(new Contact(authorName, authorWebsiteUrl, authorEmail))
                .build();
    }

    protected List<Parameter> globalOperationParameters() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();

        parameterBuilder.name(LocaleChangeInterceptor.DEFAULT_PARAM_NAME)
                .description("地区语言代码，默认zh_CN(简体中文), en_US(英文)")
                .defaultValue("zh_CN")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .required(false);

        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parameterBuilder.build());
        return parameters;
    }
}
