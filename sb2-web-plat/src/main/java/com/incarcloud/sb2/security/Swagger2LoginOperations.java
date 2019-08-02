package com.incarcloud.sb2.security;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.*;

/**
 * 支持Swagger API登录注销操作
 *
 * @author Aaric, created on 2019-08-02T10:44.
 * @since 0.6.0-SNAPSHOT
 */
public class Swagger2LoginOperations extends ApiListingScanner {

    @Autowired
    private TypeResolver typeResolver;

    public Swagger2LoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();

        /* 登录操作 */
        final List<Operation> loginOperations = new ArrayList<>();
        loginOperations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .tags(new HashSet<>(Arrays.asList("测试框架模块API")))
                .method(HttpMethod.POST)
                .uniqueId("login")
                .summary("登录")
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("*/*")))
                .notes("使用Spring Security权限控制")
                .parameters(Arrays.asList(new ParameterBuilder()
                                .name(LocaleChangeInterceptor.DEFAULT_PARAM_NAME)
                                .description("地区语言代码，默认zh_CN(简体中文), en_US(英文)")
                                .parameterType("query")
                                .modelRef(new ModelRef("string"))
                                .defaultValue("zh_CN")
                                .order(1)
                                .build(),
                        new ParameterBuilder()
                                .name("loginUserInfo")
                                .description("用户登录信息")
                                .parameterType("body")
                                .type(typeResolver.resolve(LoginUserInfo.class))
                                .modelRef(new ModelRef("LoginUserInfo"))
                                .scalarExample(new LoginUserInfo("admin", "admin"))
                                .required(true)
                                .order(2)
                                .build()))
                .build());
        apis.add(new ApiDescription("测试框架模块API", "/api/plat/test/authLogin", "Authentication documentation", loginOperations, false));

        /* 注销操作 */
        final List<Operation> logoutOperations = new ArrayList<>();
        logoutOperations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .tags(new HashSet<>(Arrays.asList("测试框架模块API")))
                .method(HttpMethod.GET)
                .uniqueId("logout")
                .summary("注销")
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("*/*")))
                .notes("使用Spring Security权限控制")
                .parameters(Arrays.asList(new ParameterBuilder()
                        .name(LocaleChangeInterceptor.DEFAULT_PARAM_NAME)
                        .description("地区语言代码，默认zh_CN(简体中文), en_US(英文)")
                        .parameterType("query")
                        .modelRef(new ModelRef("string"))
                        .defaultValue("zh_CN")
                        .order(1)
                        .build()))
                .build());
        apis.add(new ApiDescription("测试框架模块API", "/api/plat/test/authLogout", "Authentication documentation", logoutOperations, false));


        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
                .apis(apis)
                .description("Custom authentication")
                .build());

        return def;
    }
}
