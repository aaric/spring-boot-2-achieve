package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.AmapLocateProperties;
import com.incarcloud.common.map.AmapLocateService;
import com.incarcloud.common.map.impl.AmapLocateServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高德地图服务配置（基站定位）
 *
 * @author Aaric, created on 2019-11-04T11:08.
 * @version 1.2.0-SNAPSHOT
 */
@Configuration
public class BizAmapLocateConfiguration {

    @Autowired
    private AmapLocateProperties amapLocateProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.amap.apilocate", name = "key")
    public AmapLocateService amapLocateService() {
        return new AmapLocateServiceImpl(amapLocateProperties);
    }
}
