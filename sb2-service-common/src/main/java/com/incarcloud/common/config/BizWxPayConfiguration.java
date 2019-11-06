package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.WxPayProperties;
import com.incarcloud.common.pay.WxPayService;
import com.incarcloud.common.pay.impl.WxPayServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 *
 * @author Aaric, created on 2019-10-29T13:53.
 * @version 1.2.0-SNAPSHOT
 */
@Configuration
public class BizWxPayConfiguration {

    @Autowired
    private WxPayProperties wxPayProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".pay.weixin",
            name = {"appId", "mchId", "apiSecret", "notifyUrl"})
    public WxPayService wxPayService() {
        return new WxPayServiceImpl(wxPayProperties);
    }
}
