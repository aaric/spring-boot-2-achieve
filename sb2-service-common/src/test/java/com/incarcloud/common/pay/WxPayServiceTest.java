package com.incarcloud.common.pay;

import com.alibaba.fastjson.JSON;
import com.incarcloud.common.utils.PaymentUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

/**
 * WxPayServiceTest
 *
 * @author Aaric, created on 2019-10-29T13:46.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WxPayServiceTest {

    @Autowired(required = false)
    private WxPayService wxPayService;

    private String clientIp = "59.173.243.67";
    private String orderId = "201911061645520001";

    @Test
    @Disabled
    public void testCreateWebOrder() throws Exception {
        String createOrderId = PaymentUtil.createOrderId(1);
        String qrCodeUrl = wxPayService.createWebOrder(createOrderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "微信支付测试Web商品", "微信支付测试Web商品描述", clientIp);
        log.info("qcCodeUrl: {}", qrCodeUrl);
        Assertions.assertNotNull(qrCodeUrl);
    }

    @Test
    @Disabled
    public void testCreateAppOrder() throws Exception {
        String createOrderId = PaymentUtil.createOrderId(1);
        Map<String, String> sdkParams = wxPayService.createAppOrder(createOrderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "微信支付测试App商品", "微信支付测试App商品描述", clientIp);
        log.info("sdkParams: {}", JSON.toJSONString(sdkParams));
        Assertions.assertNotNull(sdkParams);
    }

    @Test
    @Disabled
    public void testQueryOrderStatus() throws Exception {
        //boolean status = wxPayService.queryOrderStatus(orderId, "e2j6zzBEtAdHmuCk");
        boolean status = wxPayService.queryOrderStatus(orderId);
        log.debug("status: {}", status);
        Assertions.assertTrue(status);
    }

    @Test
    @Disabled
    public void testRefundOrder() throws Exception {
        String refundId = PaymentUtil.getRefundIdByOrderId(orderId);
        boolean status = wxPayService.refundOrder(orderId, 0.01F, refundId);
        log.debug("status: {}", status);
        Assertions.assertTrue(status);
    }

    @Test
    @Disabled
    public void testQueryRefundOrderStatus() throws Exception {
        String refundId = PaymentUtil.getRefundIdByOrderId(orderId);
        boolean status = wxPayService.queryRefundOrderStatus(orderId, refundId);
        log.debug("status: {}", status);
        Assertions.assertTrue(status);
    }
}
