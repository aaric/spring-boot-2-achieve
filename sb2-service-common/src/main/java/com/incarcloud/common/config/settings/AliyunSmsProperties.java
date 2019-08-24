package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信服务配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   sms:
 *     aliyun:
 *       endpoint: cn-hangzhou
 *       regionId: cn-hangzhou
 *       connectTimeout: 10000
 *       readTimeout: 10000
 *       signName: 英卡科技
 *       accessKeyId: yourid
 *       accessKeySecret: yoursecret
 * </pre>
 *
 * @author Aaric, created on 2019-08-23T14:29.
 * @since 0.10.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".sms.aliyun")
public class AliyunSmsProperties {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    public static final String DEFAULT_PRODUCT = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    public static final String DEFAULT_DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 服务器端点
     */
    private static final String DEFAULT_ENDPOINT = "cn-hangzhou";

    /**
     * 服务器区域ID
     */
    private static final String DEFAULT_REGION_ID = "cn-hangzhou";

    /**
     * 服务器连接超时时间
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 10000;

    /**
     * 服务器读取超时时间
     */
    private static final int DEFAULT_READ_TIMEOUT = 10000;

    /**
     * 短信签名
     */
    private static final String DEFAULT_SIGN_NAME = "英卡科技";

    private String endpoint = DEFAULT_ENDPOINT;

    private String regionId = DEFAULT_REGION_ID;

    private Integer connectTimeout = DEFAULT_CONNECT_TIMEOUT;

    private Integer readTimeout = DEFAULT_READ_TIMEOUT;

    private String signName = DEFAULT_SIGN_NAME;

    /**
     * 访问授权KEY
     */
    private String accessKeyId = "";

    /**
     * 访问授权密钥
     */
    private String accessKeySecret = "";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
