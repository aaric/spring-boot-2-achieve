package io.sparrow.sb2.trade.service;

import com.alibaba.fastjson.JSON;
import io.sparrow.sb2.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * OrderServiceTest
 *
 * @author Aaric, created on 2019-10-29T15:58.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    private String clientIp = "59.173.243.67";

    @Test
    @Disabled
    public void testCreateAliWebOrder() throws Exception {
        PaymentDto payment = orderService.createAliWebOrder(1);
        log.debug("html: {}", payment.getContent());
        Assertions.assertNotNull(payment);
    }

    @Test
    @Disabled
    public void testCreateWxWebOrder() throws Exception {
        PaymentDto payment = orderService.createWxWebOrder(1, clientIp);
        log.debug("qcCodeUrl: {}", payment.getContent());
        Assertions.assertNotNull(payment);
    }

    @Test
    @Disabled
    public void testCreateAliAppOrder() throws Exception {
        PaymentDto payment = orderService.createAliAppOrder(1);
        log.debug("sdkParams: {}", payment.getContent());
        Assertions.assertNotNull(payment);
    }

    @Test
    @Disabled
    public void testCreateWxAppOrder() throws Exception {
        PaymentDto payment = orderService.createWxAppOrder(1, clientIp);
        log.debug("sdkParams: {}", JSON.toJSONString(payment.getData()));
        Assertions.assertNotNull(payment);
    }
}
