package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   email:
 *     host: smtp.incarcloud.com
 *     port: 25
 *     account: account@incarcloud.com
 *     password: secret
 * </pre>
 *
 * @author Aaric, created on 2019-08-22T14:02.
 * @since 0.8.1-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".email")
public class EmailProperties {

    /**
     * MiniType: TEXT/HTML
     */
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

    /**
     * 发送服务器域名
     */
    private static final String DEFAULT_HOST = "smtp." + Constant.DEFAULT_ENTERPRISE_CODE + ".com";

    /**
     * 发送服务器端口
     */
    private static final int DEFAULT_PORT = 25;

    /**
     * 授权账号
     */
    private static final String DEFAULT_ACCOUNT = "";

    /**
     * 授权密码
     */
    private static final String DEFAULT_PASSWORD = "";

    private String host = DEFAULT_HOST;

    private int port = DEFAULT_PORT;

    private String account = DEFAULT_ACCOUNT;

    private String password = DEFAULT_PASSWORD;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
