package com.incarcloud.common.config;

import com.incarcloud.common.config.settings.FtpProperties;
import com.incarcloud.common.file.FtpService;
import com.incarcloud.common.file.impl.FtpServiceImpl;
import com.incarcloud.common.share.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FTP文件服务配置
 *
 * @author Aaric, created on 2019-09-06T17:42.
 * @since 1.0-milestone
 */
@Configuration
public class BizFtpConfiguration {

    @Autowired
    private FtpProperties ftpProperties;

    @Bean
    @ConditionalOnProperty(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".file.ftp", name = {"hostname", "username", "password"})
    public FtpService ftpService() {
        return new FtpServiceImpl(ftpProperties);
    }
}
