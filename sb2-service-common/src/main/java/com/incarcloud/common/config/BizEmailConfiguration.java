package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.EmailProperties;
import com.incarcloud.common.msg.EmailService;
import com.incarcloud.common.msg.impl.EmailServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发送邮件服务配置
 *
 * @author Aaric, created on 2019-09-06T17:47.
 * @since 1.0-milestone
 */
@Configuration
public class BizEmailConfiguration {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".email", name = {"host", "account", "secret"})
    public EmailService emailService() {
        return new EmailServiceImpl(emailProperties);
    }
}
