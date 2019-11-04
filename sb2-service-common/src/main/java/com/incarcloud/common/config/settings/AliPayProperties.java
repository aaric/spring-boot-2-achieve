package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付宝服务配置类<br>
 * <ul>
 *     <li>开发文档：https://docs.open.alipay.com</li>
 *     <li>沙箱应用：https://openhome.alipay.com/platform/appDaily.htm</li>
 * </ul>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   pay: # 第三方支付
 *     alipay: # 支付宝
 *       appId: yourappid
 *       apiGatewayUrl: yourapigatewayurl
 *       apiPkcs8PublicKey: yourapipkcs8publickey
 *       apiPkcs8PrivateKey: yourapipkcs8privatekey
 *       notifyUrl: yournotifyurl
 * </pre>
 *
 * @author Aaric, created on 2019-10-30T14:33.
 * @version 1.2.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".pay.alipay")
public class AliPayProperties {

    /**
     * 请求使用的数据格式化类型，仅支持JSON
     */
    public static final String DEFAULT_API_FORMAT = "json";

    /**
     * 请求使用的编码格式，如utf-8, gbk, gb2312等
     */
    public static final String DEFAULT_API_CHARSET = "utf-8";

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    public static final String DEFAULT_API_SIGN_TYPE = "RSA2";

    /**
     * 请求支付API网关地址，默认生产环境
     */
    public static final String DEFAULT_API_GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 单允许的最晚付款时间，逾期将关闭交易
     */
    public static final String DEFAULT_API_TIMEOUT_EXPRESS = "30m";

    /**
     * 授权ID
     */
    private String appId;

    /**
     * 请求支付API网关地址
     */
    private String apiGatewayUrl;

    /**
     * 应用RSA公钥内容(BASE64编码)
     */
    private String apiPkcs8PublicKey;

    /**
     * 应用RSA私钥内容(BASE64编码)
     */
    private String apiPkcs8PrivateKey;

    /**
     * 支付结果通知地址
     */
    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiGatewayUrl() {
        return apiGatewayUrl;
    }

    public void setApiGatewayUrl(String apiGatewayUrl) {
        this.apiGatewayUrl = apiGatewayUrl;
    }

    public String getApiPkcs8PublicKey() {
        return apiPkcs8PublicKey;
    }

    public void setApiPkcs8PublicKey(String apiPkcs8PublicKey) {
        this.apiPkcs8PublicKey = apiPkcs8PublicKey;
    }

    public String getApiPkcs8PrivateKey() {
        return apiPkcs8PrivateKey;
    }

    public void setApiPkcs8PrivateKey(String apiPkcs8PrivateKey) {
        this.apiPkcs8PrivateKey = apiPkcs8PrivateKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
