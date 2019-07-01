package com.incarcloud.sb2.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger2在线文档开发者配置类<br>
 *
 * # Incar settings
 * incar:
 *   document:
 *     author:
 *       name: 英卡科技
 *       website-url: www.incarcloud.com
 *       email: incar@incarcloud.com
 *
 * @author Aaric, created on 2019-07-01T12:34.
 * @since 0.2.2-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = "incar.document.author")
public class DocumentAuthorProperties {

    private static final String DEFAULT_NAME = "英卡科技";

    private static final String DEFAULT_WEBSITE_URL = "www.incarcloud.com";

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
