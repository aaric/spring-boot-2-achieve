package com.incarcloud.common.pay;

import lombok.extern.slf4j.Slf4j;
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

    @Test
    @Ignore
    public void testCreateOrder() throws Exception {
        String html = aliPayService.createOrder("1030", "FAST_INSTANT_TRADE_PAY", 0.01F, "支付宝测试商品", "支付宝测试描述",
                "1030", "company=incar&website=incarcloud.com");
        log.info(html);
    }
}
