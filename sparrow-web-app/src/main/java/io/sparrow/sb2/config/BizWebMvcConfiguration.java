package io.sparrow.sb2.config;

import com.incarcloud.mvc.config.AbstractWebMvcConfigurationSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * 项目SpringMVC配置
 *
 * @author Aaric, created on 2019-07-16T11:17.
 * @since 0.4.2-SNAPSHOT
 */
@Configuration
//@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class BizWebMvcConfiguration extends AbstractWebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        // Add your interceptors here.
    }
}
