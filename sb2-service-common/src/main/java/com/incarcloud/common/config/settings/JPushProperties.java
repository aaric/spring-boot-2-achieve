package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 极光推送服务配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   push:
 *     jiguang:
 *       appKey: yourkey
 *       masterSecret: yoursecret
 *       apnsProduction: false
 * </pre>
 *
 * @author Aaric, created on 2019-09-04T15:37.
 * @since 0.12.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".push.jiguang")
public class JPushProperties {

    /**
     * 指定iOS环境APN证书模式: true-为生产模式, false-为测试模式
     */
    public static final boolean DEFAULT_APNS_PRODUCTION = false;

    /**
     * APP应用ID
     */
    private String appKey = "";

    /**
     * 授权密码
     */
    private String masterSecret = "";

    private Boolean apnsProduction = DEFAULT_APNS_PRODUCTION;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public Boolean getApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(Boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }
}
