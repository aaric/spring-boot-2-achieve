package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 高德地图服务配置类（基站定位）<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   map: # 高德地图 OR 百度地图 OR 腾讯地图, Others...
 *     amap: # 高德地图配置
 *       apilocate: # Locate API
 *         hostname: apilocate.amap.com
 *         key: yourkey
 * </pre>
 *
 * @author Aaric, created on 2019-11-04T11:00.
 * @version 1.2.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".map.amap.apilocate")
public class AmapLocateProperties {

    /**
     * 默认请求Locate API主机名字符串
     */
    private static final String DEFAULT_HOSTNAME = "apilocate.amap.com";

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
