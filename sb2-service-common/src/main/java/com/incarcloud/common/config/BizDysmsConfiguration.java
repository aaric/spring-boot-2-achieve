package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.DysmsProperties;
import com.incarcloud.common.share.Constant;
import com.incarcloud.common.sms.DysmsService;
import com.incarcloud.common.sms.impl.DysmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信服务配置
 *
 * @author Aaric, created on 2019-09-06T17:52.
 * @version 1.0-milestone
 */
@Configuration
public class BizDysmsConfiguration {

    @Autowired
    private DysmsProperties dysmsProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".sms.dysms", name = {"accessKeyId", "accessKeySecret"})
    public DysmsService dysmsService() {
        return new DysmsServiceImpl(dysmsProperties);
    }
}
