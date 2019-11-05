package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.AliPayProperties;
import com.incarcloud.common.pay.AliPayService;
import com.incarcloud.common.pay.impl.AliPayServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置
 *
 * @author Aaric, created on 2019-10-30T15:25.
 * @version 1.2.0-SNAPSHOT
 */
@Configuration
public class BizAliPayConfiguration {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".pay.alipay",
            name = {"appId", "apiPkcs8PublicKey", "apiPkcs8PrivateKey", "notifyUrl"})
    public AliPayService aliPayService() {
        return new AliPayServiceImpl(aliPayProperties);
    }
}
