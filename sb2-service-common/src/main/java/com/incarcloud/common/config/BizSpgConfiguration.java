package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.SpgProperties;
import com.incarcloud.common.map.SpgSevice;
import com.incarcloud.common.map.impl.SpgSeviceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基站位置服务配置(gpsspg.com)
 *
 * @author Aaric, created on 2019-10-14T14:25.
 * @since 1.1.0-SNAPSHOT
 */
@Configuration
public class BizSpgConfiguration {

    @Autowired
    private SpgProperties spgProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.gpsspg", name = {"oid", "key"})
    public SpgSevice spgSevice() {
        return new SpgSeviceImpl(spgProperties);
    }
}
