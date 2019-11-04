package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 高德地图服务配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   map: # 高德地图 OR 百度地图 OR 腾讯地图, Others...
 *     amap: # 高德地图配置
 *       hostname: restapi.amap.com
 *       key: yourkey
 * </pre>
 *
 * @author Aaric, created on 2019-09-04T17:11.
 * @version 0.13.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.amap.restapi")
public class AmapProperties {

    /**
     * 默认请求REST API主机名字符串
     */
    private static final String DEFAULT_HOSTNAME = "restapi.amap.com";

    /**
     * 主机名
     */
    private String hostname = DEFAULT_HOSTNAME;

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
