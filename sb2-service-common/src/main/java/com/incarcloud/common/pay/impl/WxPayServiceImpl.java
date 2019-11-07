package com.incarcloud.common.pay.impl;

import com.alibaba.fastjson.JSON;
import com.incarcloud.common.config.settings.WxPayProperties;
import com.incarcloud.common.data.ResponseFailureState;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.pay.WxPayService;
import com.incarcloud.common.utils.PaymentUtil;
import com.weixin.pay.sdk.IWXPayDomain;
import com.weixin.pay.sdk.WXPay;
import com.weixin.pay.sdk.WXPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                                 String goodsDesc, String clientIp) throws ApiException {
        // 构建创建支付订单业务信息
        Map<String, String> request = new HashMap<>();
        request.put("device_info", "WEB"); //终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        request.put("body", goodsName); //商品简单描述
        request.put("detail", goodsName); //商品详细描述
        request.put("out_trade_no", orderId); //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        request.put("fee_type", WxPayProperties.DEFAULT_FEE_TYPE); //币种，默认人民币：CNY
        request.put("total_fee", "" + Float.valueOf(totalAmount * 100).intValue()); //货币转换：元转分
        request.put("spbill_create_ip", clientIp); //客户端IP，支持IPV4和IPV6两种格式
        request.put("notify_url", wxPayProperties.getCallbackNotifyUrl()); //异步接收微信支付结果通知的回调地址
        request.put("trade_type", WxPayProperties.DEFAULT_TRADE_TYPE_NATIVE);  //交易类型：NATIVE-Native支付
        request.put("product_id", goodsCode); //商品ID，即商品编号，与支付宝保持统一

        // 发起支付请求
        try {
            // 调用API接口
            Map<String, String> response = wxpay.unifiedOrder(request);
            log.debug("orderId: {}, request: {}", orderId, JSON.toJSONString(response));

            // 解析支付结果
            if (null != response && 0 != response.size()) {
                // 判断请求是否成功
                if (StringUtils.equals("SUCCESS", response.get("return_code"))
                        && StringUtils.equals("OK", response.get("return_msg"))) {
                    // 返回二维码地址
                    return response.get("code_url");
                } else {
                    // 记录失败日志
                    log.error("微信订单{}创建支付订单失败，原因：{}", orderId, JSON.toJSONString(response));
                }
            }

        } catch (Exception e) {
            // 调用微信支付接口失败
            throw new ApiException(ResponseFailureState.ERROR_0062);
        }

        return null;
    }

    @Override
    public boolean queryOrderStatus(String orderId) throws ApiException {
        // 构建查询支付状态业务信息
        Map<String, String> request = new HashMap<>();
        request.put("out_trade_no", orderId); //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        //request.put("nonce_str", nonceStr); //随机字符串，API文档要求必填，但实际可以为空

        // 发起查询支付状态请求
        try {
            // 调用API接口
            Map<String, String> response = wxpay.orderQuery(request);
            log.debug("orderId: {}, request: {}", orderId, JSON.toJSONString(response));

            // 解析查询支付状态结果
            if (null != response && 0 != response.size()) {
                // 判断请求是否成功
                if (StringUtils.equals("SUCCESS", response.get("return_code"))
                        && StringUtils.equals("OK", response.get("return_msg"))) {
                    /**
                     * SUCCESS: 支付成功
                     * REFUND: 转入退款
                     * NOTPAY: 未支付
                     * CLOSED: 已关闭
                     * REVOKED: 已撤销（付款码支付）
                     * USERPAYING: 用户支付中（付款码支付）
                     * PAYERROR: 支付失败(其他原因，如银行返回失败)
                     */
                    String tradeState = response.get("trade_state");
                    if (StringUtils.equals("SUCCESS", tradeState)) {
                        // 交易成功
                        return true;
                    }
                } else {
                    // 记录失败日志
                    log.error("微信订单{}查询支付状态失败，原因：{}", orderId, JSON.toJSONString(response));
                }
            }

        } catch (Exception e) {
            // 调用微信支付接口失败
            throw new ApiException(ResponseFailureState.ERROR_0062);
        }

        return false;
    }

    @Override
    public boolean refundOrder(String orderId, float refundAmount, String refundId) throws ApiException {
        // 构建退款业务信息
        Map<String, String> request = new HashMap<>();
        request.put("out_trade_no", orderId); //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        //request.put("nonce_str", nonceStr); //随机字符串，API文档要求必填，但实际可以为空
        request.put("out_refund_no", refundId); //商户系统内部的退款单号，商户系统内部唯一
        String defaultRefundFee = "" + Float.valueOf(refundAmount * 100).intValue(); //默认全额退款，不区分订单和退款金额
        request.put("total_fee", defaultRefundFee); //订单总金额，货币转换：元转分
        request.put("refund_fee", defaultRefundFee); //退款总金额，货币转换：元转分

        // 发起退款请求
        try {
            // 调用API接口
            Map<String, String> response = wxpay.refund(request);
            log.debug("orderId: {}, request: {}", orderId, JSON.toJSONString(response));

            // 解析退款结果
            if (null != response && 0 != response.size()) {
                // 判断请求是否成功
                if (StringUtils.equals("SUCCESS", response.get("return_code"))
                        && StringUtils.equals("OK", response.get("return_msg"))) {
                    /**
                     * SUCCESS: 退款申请接收成功，结果通过退款查询接口查询
                     * FAIL: 提交业务失败
                     */
                    if (StringUtils.equals("SUCCESS", response.get("result_code"))) {
                        // 退款成功
                        return true;
                    }
                } else {
                    // 记录失败日志
                    log.error("微信订单{}退款失败，原因：{}", orderId, JSON.toJSONString(response));
                }
            }

        } catch (Exception e) {
            // 调用微信支付接口失败
            throw new ApiException(ResponseFailureState.ERROR_0062);
        }

        return false;
    }

    @Override
    public boolean queryRefundOrderStatus(String orderId, String refundId) throws ApiException {
        // 构建查询退款状态信息
        Map<String, String> request = new HashMap<>();
        request.put("out_trade_no", orderId); //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
        //request.put("nonce_str", nonceStr); //随机字符串，API文档要求必填，但实际可以为空

        // 发起查询退款状态请求
        try {
            // 调用API接口
            Map<String, String> response = wxpay.refundQuery(request);
            log.debug("orderId: {}, request: {}", orderId, JSON.toJSONString(response));

            // 解析查询退款状态结果
            if (null != response && 0 != response.size()) {
                // 判断请求是否成功
                if (StringUtils.equals("SUCCESS", response.get("return_code"))
                        && StringUtils.equals("OK", response.get("return_msg"))) {
                    /**
                     * SUCCESS: 退款申请接收成功，结果通过退款查询接口查询
                     * FAIL: 提交业务失败
                     */
                    if (StringUtils.equals("SUCCESS", response.get("result_code"))) {
                        // 退款成功
                        return true;
                    }
                } else {
                    // 记录失败日志
                    log.error("微信订单{}退款失败，原因：{}", orderId, JSON.toJSONString(response));
                }
            }

        } catch (Exception e) {
            // 调用微信支付接口失败
            throw new ApiException(ResponseFailureState.ERROR_0062);
        }

        return false;
    }

    @Override
    public boolean refundOrder(String orderId, float refundAmount) throws ApiException {
        // 注意: 退款ID='TK'+orderId
        return refundOrder(orderId, refundAmount, PaymentUtil.getRefundIdByOrderId(orderId));
    }

    @Override
    public boolean queryRefundOrderStatus(String orderId) throws ApiException {
        // 注意: 退款ID='TK'+orderId
        return queryRefundOrderStatus(orderId, PaymentUtil.getRefundIdByOrderId(orderId));
    }
}
