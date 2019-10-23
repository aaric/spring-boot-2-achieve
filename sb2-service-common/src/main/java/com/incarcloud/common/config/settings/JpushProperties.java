package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 极光推送服务配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   push: # 极光推送 OR 个推服务
 *     jpush: # JPush配置
 *       appKey: yourkey
 *       masterSecret: yoursecret
 *       apnsProduction: false
 * </pre>
 *
 * @author Aaric, created on 2019-09-04T15:37.
 * @version 0.12.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".push.jpush")
public class JpushProperties {

    /**
     * 默认iOS环境APN证书为生产模式
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

    /**
     * 指定iOS环境APN证书模式: true-为生产模式, false-为测试模式
     */
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
