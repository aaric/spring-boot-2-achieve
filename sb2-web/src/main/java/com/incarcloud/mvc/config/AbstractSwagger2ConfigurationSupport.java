package com.incarcloud.mvc.config;

import com.incarcloud.mvc.config.settings.Swagger2ApiProperties;
import com.incarcloud.mvc.config.settings.Swagger2AuthorProperties;
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
 * 默认Swagger2配置
 *
 * @author Aaric, created on 2019-06-27T17:27.
 * @since 0.2.1-SNAPSHOT
 */
public abstract class AbstractSwagger2ConfigurationSupport {

    @Autowired
    protected Swagger2ApiProperties swagger2ApiProperties;

    @Autowired
    protected Swagger2AuthorProperties swagger2AuthorProperties;

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger2ApiProperties.getTitle())
                .description(swagger2ApiProperties.getDescription())
                .termsOfServiceUrl(swagger2ApiProperties.getServiceUrl())
                .version(swagger2ApiProperties.getVersion())
                .contact(new Contact(swagger2AuthorProperties.getName(), swagger2AuthorProperties.getWebsiteUrl(), swagger2AuthorProperties.getEmail()))
                .build();
    }

    /**
     * 注册国际化参数信息
     *
     * @return
     */
    protected List<Parameter> globalOperationParameters() {
        ParameterBuilder builder = new ParameterBuilder();

        builder.name(LocaleChangeInterceptor.DEFAULT_PARAM_NAME)
                .description("地区语言代码，默认zh_CN(简体中文), en_US(英文)")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .defaultValue("zh_CN")
                .required(false);

        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(builder.build());
        return parameters;
    }
}
