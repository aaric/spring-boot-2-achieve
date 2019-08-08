package com.incarcloud.mvc.token.settings;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT服务端配置<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   auth:
 *     jwt:
 *       secret-key: A3MZkHTQ6db/kOP6Q3F3IGmXTfES9BUIN0PHfnG8uCY=
 *       token-header-name: x-access-token
 *       token-active-seconds: 7*24*60*60
 * </pre>
 *
 * @author Aaric, created on 2019-08-08T12:38.
 * @since 0.7.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".auth.jwt")
public class AuthJwtProperties {

    /**
     * 服务端创建Token密钥
     */
    private static final String DEFAULT_SECRET_KEY = "";

    /**
     * 客户端Token请求Header名称
     */
    private static final String DEFAULT_TOKEN_HEADER_NAME = "x-access-token";

    /**
     * 客户端Token活跃周期
     */
    private static final long DEFAULT_TOKEN_ACTIVE_SECONDS = 7 * 24 * 60 * 60;

    private String secretKey = DEFAULT_SECRET_KEY;

    private String tokenHeaderName = DEFAULT_TOKEN_HEADER_NAME;

    private Long tokenActiveSeconds = DEFAULT_TOKEN_ACTIVE_SECONDS;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenHeaderName() {
        return tokenHeaderName;
    }

    public void setTokenHeaderName(String tokenHeaderName) {
        this.tokenHeaderName = tokenHeaderName;
    }

    public Long getTokenActiveSeconds() {
        return tokenActiveSeconds;
    }

    public void setTokenActiveSeconds(Long tokenActiveSeconds) {
        this.tokenActiveSeconds = tokenActiveSeconds;
    }
}
