package com.incarcloud.common.pay.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.incarcloud.common.config.settings.AliPayProperties;
import com.incarcloud.common.data.ResponseFailureState;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.pay.AliPayService;
import com.incarcloud.common.utils.PaymentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 支付宝服务实现
 *
 * @author Aaric, created on 2019-10-30T16:00.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    /**
     * 支付宝服务配置
     */
    private AliPayProperties aliPayProperties;

    /**
     * 支付宝客户端
     */
    private AlipayClient alipayClient;

    public AliPayServiceImpl(AliPayProperties aliPayProperties) {
        this.aliPayProperties = aliPayProperties;
        // 初始化客户端
        alipayClient = new DefaultAlipayClient(
                aliPayProperties.getAlipayGatewayUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getAppPrivateKey(),
                AliPayProperties.DEFAULT_API_FORMAT,
                AliPayProperties.DEFAULT_API_CHARSET,
                aliPayProperties.getAlipayPublicKey(),
                AliPayProperties.DEFAULT_API_SIGN_TYPE
        );
    }

    @Override
    public String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                                 String goodsDesc) throws ApiException {
        return createWebOrder(orderId, goodsCode, totalAmount, goodsName, goodsDesc, null, null);
    }

    @Override
    public String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                                 String goodsDesc, String refId, String passbackParams) throws ApiException {
        // 构建创建支付订单业务信息
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderId); //商户订单号
        model.setProductCode(goodsCode); //销售产品码，与支付宝签约的产品码名称
        model.setTotalAmount(String.valueOf(totalAmount)); //订单总金额，单位：元
        model.setSubject(goodsName); //订单名称
        model.setBody(goodsDesc); //订单描述

        // 公用回传参数
        if (StringUtils.isNotBlank(passbackParams)) {
            try {
                model.setPassbackParams(URLEncoder.encode(passbackParams, AliPayProperties.DEFAULT_API_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error("URL编码失败", e);
            }
        }

        // 设置业务扩展参数
        if (StringUtils.isNotBlank(refId)) {
            // 系统商编号
            ExtendParams extendParams = new ExtendParams();
            extendParams.setSysServiceProviderId(refId);
            model.setExtendParams(extendParams);
        }

        // 设置该笔订单允许的最晚付款时间，逾期将关闭交易
        model.setTimeoutExpress(AliPayProperties.DEFAULT_API_TIMEOUT_EXPRESS); //30分钟

        // 创建支付请求
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model); //设置业务实体
        request.setNotifyUrl(aliPayProperties.getCallbackNotifyUrl()); //设置支付成功通知地址
        request.setReturnUrl(aliPayProperties.getWebReturnUrl());  //设置支付成功跳转地址

        // 发起支付请求
        try {
            // 调用API接口
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                // 立即支付表单HTML
                return response.getBody();
            } else {
                // 记录失败日志
                log.error("支付宝订单{}创建支付订单失败，原因：{}", orderId, StringUtils.defaultIfEmpty(response.getMsg(), response.getSubMsg()));
            }
        } catch (AlipayApiException e) {
            // 调用支付宝接口失败
            throw new ApiException(ResponseFailureState.ERROR_0061);
        }

        return null;
    }

    @Override
    public boolean queryOrderStatus(String orderId) throws ApiException {
        // 构建查询订单状态业务信息
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(orderId); //商户订单号

        // 创建查询状态请求
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model); //设置业务实体

        // 发起查询状态请求
        try {
            // 调用API接口
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                /**
                 * WAIT_BUYER_PAY: 交易创建，等待买家付款
                 * TRADE_CLOSED: 未付款交易超时关闭，或支付完成后全额退款
                 * TRADE_SUCCESS: 交易支付成功
                 * TRADE_FINISHED: 交易结束，不可退款
                 */
                String tradeStatus = response.getTradeStatus();
                if (StringUtils.equals("TRADE_SUCCESS", tradeStatus)
                        || StringUtils.equals("TRADE_FINISHED", tradeStatus)) {
                    // 交易成功
                    return true;
                } else {
                    // 记录失败日志
                    log.error("支付宝订单{}查询订单状态失败，原因：{}", orderId, StringUtils.defaultIfEmpty(response.getMsg(), response.getSubMsg()));
                }

            }
        } catch (AlipayApiException e) {
            // 调用支付宝接口失败
            throw new ApiException(ResponseFailureState.ERROR_0061);
        }

        return false;
    }

    @Override
    public boolean refundOrder(String orderId, float refundAmount, String refundId) throws ApiException {
        // 构建退款业务信息
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(orderId); //商户订单号
        model.setRefundAmount(String.valueOf(refundAmount)); //退款金额
        model.setOutRequestNo(refundId); //标识一次退款请求，同一笔交易多次退款需要保证唯一

        // 创建退款请求
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model); //设置业务实体

        // 发起退款请求
        try {
            // 调用API接口
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                String fundChange = response.getFundChange();
                if (StringUtils.equals("Y", fundChange)) {
                    // 退款成功
                    return true;
                } else {
                    // 记录失败日志
                    log.error("支付宝订单{}退款失败，原因：{}", orderId, StringUtils.defaultIfEmpty(response.getMsg(), response.getSubMsg()));
                }
            }
        } catch (AlipayApiException e) {
            // 调用支付宝接口失败
            throw new ApiException(ResponseFailureState.ERROR_0061);
        }

        return false;
    }

    @Override
    public boolean queryRefundOrderStatus(String orderId, String refundId) throws ApiException {
        // 构建查询退款状态业务信息
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderId);
        model.setOutRequestNo(refundId);

        // 创建查询退款状态请求
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model); //设置业务实体

        // 发起查询退款状态请求
        try {
            // 调用API接口
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                String requestNo = response.getOutRequestNo();
                if (StringUtils.equals(refundId, requestNo)) {
                    // 退款成功
                    return true;
                } else {
                    // 记录失败日志
                    log.error("支付宝订单{}查询退款状态失败，原因：{}", orderId, StringUtils.defaultIfEmpty(response.getMsg(), response.getSubMsg()));
                }
            }
        } catch (AlipayApiException e) {
            // 调用支付宝接口失败
            throw new ApiException(ResponseFailureState.ERROR_0061);
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
