package com.incarcloud.mvc.config.settings;

import com.incarcloud.common.share.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger2在线文档开发者配置类<br>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   swagger2:
 *     author:
 *       name: 英卡科技
 *       website-url: www.incarcloud.com
 *       email: incar@incarcloud.com
 * </pre>
 *
 * @author Aaric, created on 2019-07-01T12:34.
 * @version 0.2.2-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".swagger2.author")
public class Swagger2AuthorProperties {

    /**
     * 开发者名称
     */
    private static final String DEFAULT_NAME = "英卡科技";

    /**
     * 开发者主页
     */
    private static final String DEFAULT_WEBSITE_URL = "www.incarcloud.com";

    /**
     * 开发者邮箱地址
     */
    private static final String DEFAULT_EMAIL = "incar@incarcloud.com";

    private String name = DEFAULT_NAME;

    private String websiteUrl = DEFAULT_WEBSITE_URL;

    private String email = DEFAULT_EMAIL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
