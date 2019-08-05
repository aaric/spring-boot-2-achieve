package com.incarcloud.sb2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * 项目SpringMVC配置
 *
 * @author Aaric, created on 2019-07-16T11:21.
 * @since 0.4.2-SNAPSHOT
 */
@Configuration
//@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class BizWebMvcConfiguration extends DefaultWebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        // Add your interceptors here.
    }
}
