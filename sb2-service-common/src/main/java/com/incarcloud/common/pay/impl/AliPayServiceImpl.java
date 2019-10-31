package com.incarcloud.common.pay.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.incarcloud.common.config.settings.AliPayProperties;
import com.incarcloud.common.data.ResponseFailureState;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.pay.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
                aliPayProperties.getApiGatewayUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getApiPkcs8PrivateKey(),
                AliPayProperties.DEFAULT_API_FORMAT,
                AliPayProperties.DEFAULT_API_CHARSET,
                aliPayProperties.getApiPkcs8PublicKey(),
                AliPayProperties.DEFAULT_API_SIGN_TYPE
        );
    }

    @Override
    public String createOrder(String orderId, String goodsCode, float totalAmount, String goodsName, String goodsDesc,
                              String refId, String passbackParams) throws Exception {
        // 构建支付业务信息
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderId); //商户订单号
        model.setProductCode(goodsCode); //销售产品码，与支付宝签约的产品码名称
        model.setTotalAmount(String.valueOf(totalAmount)); //订单总金额，单位：元
        model.setSubject(goodsName); //订单名称
        model.setBody(goodsDesc); //订单描述

        // 公用回传参数
        if (StringUtils.isNotBlank(passbackParams)) {
            model.setPassbackParams(URLEncoder.encode(passbackParams, AliPayProperties.DEFAULT_API_CHARSET));
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
        request.setNotifyUrl(aliPayProperties.getNotifyUrl()); //设置回跳和通知地址

        // 发起支付请求
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (!response.isSuccess()) {
            // 调用支付宝接口失败
            throw new ApiException(ResponseFailureState.ERROR_0061);
        }

        // 立即支付表单HTML
        return response.getBody();
    }
}
