package io.sparrow.sb2.trade.service.impl;

import com.alibaba.fastjson.JSON;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.pay.AliPayService;
import com.incarcloud.common.pay.WxPayService;
import com.incarcloud.common.utils.PaymentUtil;
import io.sparrow.sb2.dto.PaymentDto;
import io.sparrow.sb2.trade.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单管理服务实现
 *
 * @author Aaric, created on 2019-10-29T15:58.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 支付宝
     */
    @Autowired
    private AliPayService aliPayService;

    /**
     * 微信支付
     */
    @Autowired
    private WxPayService wxPayService;

    @Override
    public PaymentDto createAliWebOrder(Integer goodsId) throws ApiException {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给AliPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = PaymentUtil.createOrderId(1);

        // 支付宝
        String html = aliPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "支付宝测试Web商品", "支付宝测试Web商品描述");
        log.info("html: {}", html);

        return new PaymentDto(orderId, html);
    }

    @Override
    public PaymentDto createWxWebOrder(Integer goodsId, String clientIp) throws ApiException {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给WxPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = PaymentUtil.createOrderId(1);

        // 微信支付
        String qrCodeUrl = wxPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "微信支付测试Web商品", "微信支付测试Web商品描述", clientIp);
        log.info("qcCodeUrl: {}", qrCodeUrl);

        return new PaymentDto(orderId, qrCodeUrl);
    }

    @Override
    public PaymentDto createAliAppOrder(Integer goodsId) throws ApiException {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给AliPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = PaymentUtil.createOrderId(1);

        // 支付宝
        String sdkParams = aliPayService.createAppOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "支付宝测试App商品", "支付宝测试App商品描述");
        log.info("sdkParams: {}", sdkParams);

        return new PaymentDto(orderId, sdkParams);
    }

    @Override
    public PaymentDto createWxAppOrder(Integer goodsId, String clientIp) throws ApiException {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给WxPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = PaymentUtil.createOrderId(1);

        // 微信支付
        Map<String, String> sdkParams = wxPayService.createAppOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "微信支付测试App商品", "微信支付测试App商品描述", clientIp);
        log.info("sdkParams: {}", JSON.toJSONString(sdkParams));

        return new PaymentDto(orderId, sdkParams);
    }
}
