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

    @Test
    @Ignore
    public void testCreateWebOrder() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderId = MessageFormat.format("{0}0001", dateFormat.format(Date.from(Instant.now())));
        String html = aliPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F, "支付宝测试商品", "支付宝测试描述",
                "1030", "company=incar&website=incarcloud.com");
        log.debug("html: {}", html);
        Assert.assertNotNull(html);
    }

    @Test
    @Ignore
    public void testQueryOrderStatus() throws Exception {
        String orderId = "201911061752110001";
        boolean status = aliPayService.queryOrderStatus(orderId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testRefundOrder() throws Exception {
        String orderId = "201911061752110001";
        boolean status = aliPayService.refundOrder(orderId, 0.01F);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void testQueryRefundOrderStatus() throws Exception {
        String orderId = "201911061752110001";
        boolean status = aliPayService.queryRefundOrderStatus(orderId);
        log.debug("status: {}", status);
        Assert.assertTrue(status);
    }
}
