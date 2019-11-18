package io.sparrow.sb2.config;

import com.incarcloud.base.service.LogService;
import com.incarcloud.common.share.Constant;
import com.incarcloud.mvc.config.AbstractWebMvcConfigurationSupport;
import com.incarcloud.mvc.servlet.DbLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * 项目SpringMVC配置
 *
 * @author Aaric, created on 2019-07-16T11:21.
 * @version 0.4.2-SNAPSHOT
 */
@Configuration
//@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class BizWebMvcConfiguration extends AbstractWebMvcConfigurationSupport {

    /**
     * 定义业务标签
     */
    @Value("${" + Constant.DEFAULT_ENTERPRISE_CODE + ".biz.tag" + "}")
    private String bizTag;

    /**
     * 日志服务
     */
    @Autowired
    private LogService logService;

    /**
     * 初始化日志拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(DbLogInterceptor.class)
    public DbLogInterceptor dbLogInterceptor() {
        return new DbLogInterceptor(bizTag, logService, () -> {
            // 从Security获取登录用户UID
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                return authentication.getName();
            }
            return null;
        });
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // Super
        super.addInterceptors(registry);

        // DbLog
        registry.addInterceptor(dbLogInterceptor()).addPathPatterns("/api/**");

        // Add your interceptors here.
    }
}
