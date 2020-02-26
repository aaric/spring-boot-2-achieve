package com.incarcloud.mvc.config.settings;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger2在线文档API配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   swagger2:
 *     api:
 *       title: 英卡示例项目在线API文档
 *       description: 本文档包含该项目全部业务接口，注意保密。
 *       service-url: http://localhost:9080/doc.html
 *       version: 0.2.1-SNAPSHOT
 * </pre>
 *
 * @author Aaric, created on 2019-07-01T09:58.
 * @version 0.2.2-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".swagger2.api")
public class Swagger2ApiProperties {

    /**
     * 文档标题
     */
    private static final String DEFAULT_TITLE = "英卡示例项目在线API文档";

    /**
     * 文档描述
     */
    private static final String DEFAULT_DESCRIPTION = "本文档包含该项目全部业务接口，注意保密。";

    /**
     * 文档地址
     */
    private static final String DEFAULT_SERVICE_URL = "http://localhost:8080/doc.html";

    /**
     * 文档版本
     */
    private static final String DEFAULT_VERSION = "0.0.1-SNAPSHOT";

    private String title = DEFAULT_TITLE;

    private String description = DEFAULT_DESCRIPTION;

    private String serviceUrl = DEFAULT_SERVICE_URL;

    private String version = DEFAULT_VERSION;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
