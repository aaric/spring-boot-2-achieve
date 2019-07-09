package com.incarcloud.sb2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
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
    public Validator validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        //System.out.println("-->" + messageSource);
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
