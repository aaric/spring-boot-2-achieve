package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.JpushProperties;
import com.incarcloud.common.push.JpushService;
import com.incarcloud.common.push.impl.JpushServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 极光推送服务配置
 *
 * @author Aaric, created on 2019-09-06T17:56.
 * @since 1.0-milestone
 */
@Configuration
public class BizJpushConfiguration {

    @Autowired
    private JpushProperties jpushProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".push.jpush", name = {"appKey", "masterSecret"})
    public JpushService jpushService() {
        return new JpushServiceImpl(jpushProperties);
    }
}
