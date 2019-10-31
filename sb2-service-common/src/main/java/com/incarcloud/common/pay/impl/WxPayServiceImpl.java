package com.incarcloud.common.pay.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incarcloud.common.config.settings.WxPayProperties;
import com.incarcloud.common.pay.WxPayService;
import com.weixin.pay.sdk.IWXPayDomain;
import com.weixin.pay.sdk.WXPay;
import com.weixin.pay.sdk.WXPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付服务实现
 *
 * @author Aaric, created on 2019-10-29T13:42.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    /**
     * 微信支付配置
     */
    private WxPayProperties wxPayProperties;

    /**
     * 微信支付实例
     */
    private WXPay wxpay;

    public WxPayServiceImpl(WxPayProperties wxPayProperties) {
        WXPayConfig config = new WXPayConfig() {

            @Override
            public String getAppID() {
                return wxPayProperties.getAppId();
            }

            @Override
            public String getMchID() {
                return wxPayProperties.getMchId();
            }

            @Override
            public String getKey() {
                return wxPayProperties.getApiSecret();
            }

            @Override
            public InputStream getCertStream() {
                byte[] pkcs12CertBytes = Base64.getDecoder().decode(wxPayProperties.getApiPkcs12Cert());
                return new ByteArrayInputStream(pkcs12CertBytes);
            }

            @Override
            public IWXPayDomain getWXPayDomain() {
                // 配置域名信息
                return new IWXPayDomain() {

                    @Override
                    public void report(String domain, long elapsedTimeMillis, Exception ex) {

                    }

                    @Override
                    public DomainInfo getDomain(WXPayConfig config) {
                        return new DomainInfo(
                                wxPayProperties.getApiDomainString(),
                                true
                        );
                    }
                };
            }
        };
        this.wxPayProperties = wxPayProperties;
        try {
            this.wxpay = new WXPay(config);
        } catch (Exception e) {
            log.error("Create WXPay Error", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public String createOrder(String productId, String orderId, String orderName, float totalAmount, String clientIp) throws Exception {
        // 订单信息
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", orderName);
        data.put("out_trade_no", orderId);
        data.put("device_info", "");
        data.put("fee_type", WxPayProperties.DEFAULT_FEE_TYPE);
        data.put("total_fee", "" + Float.valueOf(totalAmount * 100).intValue()); //货币转换：元转分
        data.put("spbill_create_ip", clientIp);
        data.put("notify_url", wxPayProperties.getNotifyUrl());
        data.put("trade_type", WxPayProperties.DEFAULT_TRADE_TYPE_NATIVE);  //此处指定为扫码支付
        data.put("product_id", productId);

        // 发起订单支付
        Map<String, String> result = wxpay.unifiedOrder(data);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        log.info("result: {}", mapper.writeValueAsString(result));

        return null;
    }
}
