package com.incarcloud.common.pay;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    //@Ignore
    public void testCreateOrder() throws Exception {
        String result = wxPayService.createOrder("1030", "2016090910595900000012", "微信支付测试商品", 0.01F, "59.173.243.67");
        log.info("result: {}", result);
    }
}
