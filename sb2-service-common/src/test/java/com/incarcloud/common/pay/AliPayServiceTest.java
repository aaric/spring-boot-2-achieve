package com.incarcloud.common.pay;

import com.incarcloud.common.utils.PaymentUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AliPayServiceTest
 *
 * @author Aaric, created on 2019-10-30T16:02.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AliPayServiceTest {

    @Autowired(required = false)
    private AliPayService aliPayService;

    private String orderId = "201911071550450001";

    @Test
    @Ignore
    public void testCreateWebOrder() throws Exception {
        String createOrderId = PaymentUtil.createOrderId(1);
        String page = aliPayService.createWebOrder(createOrderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "支付宝测试Web商品", "支付宝测试Web商品描述",
                "1030", "company=incar&website=incarcloud.com");
        log.debug("page: {}", page);
        Assert.assertNotNull(page);
    }

    @Test
    @Ignore
    public void testCreateAppOrder() throws Exception {
        String createOrderId = PaymentUtil.createOrderId(1);
        String sdkParams = aliPayService.createAppOrder(createOrderId, "FAST_INSTANT_TRADE_PAY", 0.01F,
                "支付宝测试App商品", "支付宝测试App商品描述",
                "1030", "company=incar&website=incarcloud.com");
        log.debug("sdkParams: {}", sdkParams);
        Assert.assertNotNull(sdkParams);
    }

    @Test
    @Ignore
    public void testQueryOrderStatus() throws Exception {
        boolean status = aliPayService.queryOrderStatus(orderId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testRefundOrder() throws Exception {
        String refundId = PaymentUtil.getRefundIdByOrderId(orderId);
        boolean status = aliPayService.refundOrder(orderId, 0.01F, refundId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testQueryRefundOrderStatus() throws Exception {
        String refundId = PaymentUtil.getRefundIdByOrderId(orderId);
        boolean status = aliPayService.queryRefundOrderStatus(orderId, refundId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }
}
