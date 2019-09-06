package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FTP配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   file: # FTP OR FastDFS
 *     ftp: # FTP配置
 *       port: 127.0.0.1
 *       hostname: 21
 *       username: anonymous
 *       password: anonymous
 *       connectTimeout: 60000
 *       dataTimeout: 60000
 * </pre>
 *
 * @author Aaric, created on 2019-08-20T15:14.
 * @since 0.8.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".file.ftp")
public class FtpProperties {

    /**
     * UTF-8
     */
    public static final String DEFAULT_HTTP_ENCODING_UTF_8 = "UTF-8";

    /**
     * ISO-8859-1
     */
    public static final String DEFAULT_HTTP_ENCODING_ISO_8859_1 = "ISO-8859-1";

    /**
     * FTP端口
     */
    private static final int DEFAULT_FTP_PORT = 21;

    /**
     * FTP主机名称或者IP地址
     */
    private static final String DEFAULT_FTP_HOSTNAME = "localhost";

    /**
     * 访问FTP授权用户名
     */
    private static final String DEFAULT_FTP_USERNAME = "anonymous";

    /**
     * 访问FTP授权密码
     */
    private static final String DEFAULT_FTP_PASSWORD = "anonymous";

    /**
     * FTP连接超时时间
     */
    private static final int DEFAULT_FTP_CONNECT_TIMEOUT = 60000;

    /**
     * FTP数据传输超时时间
     */
    private static final int DEFAULT_FTP_DATA_TIMEOUT = 60000;

    private Integer port = DEFAULT_FTP_PORT;

    private String hostname = DEFAULT_FTP_HOSTNAME;

    private String username = DEFAULT_FTP_USERNAME;

    private String password = DEFAULT_FTP_PASSWORD;

    private Integer connectTimeout = DEFAULT_FTP_CONNECT_TIMEOUT;

    private Integer dataTimeout = DEFAULT_FTP_DATA_TIMEOUT;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(Integer dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
