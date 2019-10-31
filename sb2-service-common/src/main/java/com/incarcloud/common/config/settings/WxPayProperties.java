package com.incarcloud.common.config.settings;

import com.incarcloud.common.share.Constant;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付服务配置类<br>
 * <ul>
 *     <li>开发文档：https://pay.weixin.qq.com/wiki/doc/api/index.html</i></li>
 *     <li>API证书：https://kf.qq.com/product/wechatpaymentmerchant.html#hid=2874</li>
 * </ul>
 *
 * <pre>
 * # Incarcloud settings
 * incarcloud:
 *   payment: # 第三方支付
 *     wxpay: # 微信支付
 *       appId: yourappid
 *       mchId: yourmchid
 *       apiSecret: yourapisecret
 *       # cat apiclient_cert.p12 | base64 -w 0
 *       apiPkcs12Cert: yourapipkcs12cert
 *       apiDomainString: yourdomainString
 *       notifyUrl: yournotifyUrl
 * </pre>
 *
 * @author Aaric, created on 2019-10-29T14:03.
 * @version 1.2.0-SNAPSHOT
 * @see
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".payment.wxpay")
public class WxPayProperties {

    /**
     * 交易货币类型：人民币
     */
    public static final String DEFAULT_FEE_TYPE = "CNY";

    /**
     * 交易类型：JSAPI支付
     */
    public static final String DEFAULT_TRADE_TYPE_JSAPI = "JSAPI";

    /**
     * 交易类型：Native支付
     */
    public static final String DEFAULT_TRADE_TYPE_NATIVE = "NATIVE";

    /**
     * 交易类型：APP支付
     */
    public static final String DEFAULT_TRADE_TYPE_APP = "APP";

    /**
     * 授权ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * API证书密钥
     */
    private String apiSecret;

    /**
     * API证书内容(BASE64编码)
     */
    private String apiPkcs12Cert;

    /**
     * 请求支付API域名
     */
    private String apiDomainString;

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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getApiPkcs12Cert() {
        return apiPkcs12Cert;
    }

    public void setApiPkcs12Cert(String apiPkcs12Cert) {
        this.apiPkcs12Cert = apiPkcs12Cert;
    }

    public String getApiDomainString() {
        return apiDomainString;
    }

    public void setApiDomainString(String apiDomainString) {
        this.apiDomainString = apiDomainString;
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
