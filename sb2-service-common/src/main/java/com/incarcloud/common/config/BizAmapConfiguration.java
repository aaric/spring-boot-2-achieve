package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.AmapProperties;
import com.incarcloud.common.map.AmapService;
import com.incarcloud.common.map.impl.AmapServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高德地图服务配置
 *
 * @author Aaric, created on 2019-09-06T17:17.
 * @version 1.0-milestone
 */
@Configuration
public class BizAmapConfiguration {

    @Autowired
    private AmapProperties amapProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.amap", name = "key")
    public AmapService amapService() {
        return new AmapServiceImpl(amapProperties);
    }
}
