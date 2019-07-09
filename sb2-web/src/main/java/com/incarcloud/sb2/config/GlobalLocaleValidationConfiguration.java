package com.incarcloud.sb2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 数据校验本地化配置
 *
 * @author Aaric, created on 2019-07-09T10:37.
 * @since 0.4.0-SNAPSHOT
 */
@Configuration
public class GlobalLocaleValidationConfiguration {

    @Autowired
    private MessageSource messageSource;

    @Bean
    //@ConditionalOnMissingBean(LocalValidatorFactoryBean.class)
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
