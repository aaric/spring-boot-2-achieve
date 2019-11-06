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
 *     alibaba: # 支付宝
 *       appId: yourappid
 *       appPrivateKey: yourappprivatekey
 *       alipayGatewayUrl: youralipaygatewayurl
 *       alipayPublicKey: youralipaypublickey
 *       callbackNotifyUrl: yourcallbacknotifyurl
 *       webReturnUrl: yourwebreturnurl
 * </pre>
 *
 * @author Aaric, created on 2019-10-30T14:33.
 * @version 1.2.0-SNAPSHOT
 */
@Component
@ConfigurationProperties(prefix = Constant.DEFAULT_ENTERPRISE_CODE + ".pay.alibaba")
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
     * 应用RSA私钥内容(pkcs8)<br>
     * <i>商户自己生成的RSA私钥（与应用公钥必须匹配），商户开发者使用应用私钥对请求字符串进行加签。</i>
     */
    private String appPrivateKey;

    /**
     * 请求支付API网关地址
     */
    private String alipayGatewayUrl;

    /**
     * 支付宝RSA公钥内容(pkcs8)<br>
     * <i>支付宝的RSA公钥，商户使用该公钥验证该结果是否是支付宝返回的。</i><br>
     * <p>
     * Error: com.alipay.api.AlipayApiException: sign check fail: check Sign and Data Fail!<br>
     * Reason: 非应用公钥，而是支付宝公钥，用于查询状态和退款
     * </p>
     */
    private String alipayPublicKey;

    /**
     * 支付成功通知地址
     */
    private String callbackNotifyUrl;

    /**
     * 支付成功跳转地址（电脑网站支付）
     */
    private String webReturnUrl = "";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAlipayGatewayUrl() {
        return alipayGatewayUrl;
    }

    public void setAlipayGatewayUrl(String alipayGatewayUrl) {
        this.alipayGatewayUrl = alipayGatewayUrl;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getCallbackNotifyUrl() {
        return callbackNotifyUrl;
    }

    public void setCallbackNotifyUrl(String callbackNotifyUrl) {
        this.callbackNotifyUrl = callbackNotifyUrl;
    }

    public String getWebReturnUrl() {
        return webReturnUrl;
    }

    public void setWebReturnUrl(String webReturnUrl) {
        this.webReturnUrl = webReturnUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
