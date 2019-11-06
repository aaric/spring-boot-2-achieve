package com.incarcloud.common.pay;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * WxPayServiceTest
 *
 * @author Aaric, created on 2019-10-29T13:46.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class WxPayServiceTest {

    @Autowired(required = false)
    private WxPayService wxPayService;

    @Test
    @Ignore
    public void testCreateWebOrder() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderId = MessageFormat.format("{0}0001", dateFormat.format(Date.from(Instant.now())));
        String qrCodeUrl = wxPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F, "微信支付测试商品", "微信支付测试描述", "59.173.243.67");
        log.info("qcCodeUrl: {}", qrCodeUrl);
        Assert.assertNotNull(qrCodeUrl);
    }

    @Test
    @Ignore
    public void testQueryOrderStatus() throws Exception {
        String orderId = "201911061758540001";
        //boolean status = wxPayService.queryOrderStatus(orderId, "e2j6zzBEtAdHmuCk");
        boolean status = wxPayService.queryOrderStatus(orderId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testRefundOrder() throws Exception {
        String orderId = "201911061758540001";
        boolean status = wxPayService.refundOrder(orderId, 0.01F);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testQueryRefundOrderStatus() throws Exception {
        String orderId = "201911061758540001";
        boolean status = wxPayService.queryRefundOrderStatus(orderId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }
}
