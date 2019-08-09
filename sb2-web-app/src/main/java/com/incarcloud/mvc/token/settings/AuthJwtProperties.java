package com.incarcloud.mvc.token.settings;

import com.incarcloud.common.share.Constant;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Base64;

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
 *       token-lease-seconds: 7*24*60*60
 * </pre>
 *
 * @author Aaric, created on 2019-08-08T12:38.
 * @since 0.7.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".auth.jwt")
public class AuthJwtProperties {

    /**
     * 服务端创建Token密钥，如果不指定，每次重启会不一样
     */
    private static final String DEFAULT_SECRET_KEY = Base64.getEncoder()
            .encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());

    /**
     * 客户端ID请求Header名称
     */
    public static final String DEFAULT_CID_HEADER_NAME = "x-access-cid";

    /**
     * 客户端Token请求Header名称
     */
    public static final String DEFAULT_TOKEN_HEADER_NAME = "x-access-token";

    /**
     * 客户端Token租用周期，默认一个星期
     */
    public static final long DEFAULT_TOKEN_LEASE_SECONDS = 7 * 24 * 60 * 60;

    private String secretKey = DEFAULT_SECRET_KEY;

    private String tokenHeaderName = DEFAULT_TOKEN_HEADER_NAME;

    private Long tokenLeaseSeconds = DEFAULT_TOKEN_LEASE_SECONDS;

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

    public Long getTokenLeaseSeconds() {
        return tokenLeaseSeconds;
    }

    public void setTokenLeaseSeconds(Long tokenLeaseSeconds) {
        this.tokenLeaseSeconds = tokenLeaseSeconds;
    }
}
