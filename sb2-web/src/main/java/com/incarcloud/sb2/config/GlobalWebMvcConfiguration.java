package com.incarcloud.sb2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

import java.util.Locale;

/**
 * SpringMVC配置
 *
 * @author Aaric, created on 2019-07-09T13:51.
 * @since 0.4.1-SNAPSHOT
 */
@Configuration
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class GlobalWebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    protected MessageSource messageSource;

    @Bean
    @ConditionalOnMissingBean(LocaleResolver.class)
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return resolver;
    }

    @Bean
    @ConditionalOnMissingBean(LocaleChangeInterceptor.class)
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    @ConditionalOnMissingBean(Validator.class)
    public Validator validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        System.out.println("-->" + messageSource);
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Override
    protected Validator getValidator() {
        return validator();
    }
}
