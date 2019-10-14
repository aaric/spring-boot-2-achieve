package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基站位置服务配置类(gpsspg.com)<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   map: # 高德地图 OR 百度地图 OR 腾讯地图, Others...
 *     gpsspg: # gpsspg.com
 *       oid: youroid
 *       key: yourkey
 * </pre>
 *
 * @author Aaric, created on 2019-10-14T14:25.
 * @since 1.1.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.gpsspg")
public class SpgProperties {

    /**
     * 默认请求REST API主机名字符串
     */
    private static final String DEFAULT_HOSTNAME = "api.gpsspg.com";

    /**
     * 主机名
     */
    private String hostname = DEFAULT_HOSTNAME;

    /**
     * 组织ID
     */
    private String oid = "";

    /**
     * 授权KEY
     */
    private String key = "";

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
