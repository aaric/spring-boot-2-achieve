package com.incarcloud.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * PaymentUtilTest
 *
 * @author Aaric, created on 2019-11-07T15:07.
 * @version 1.2.0-SNAPSHOT
 */
public class PaymentUtilTest {

    @Test
    public void testCreateOrderId() {
        String orderId = PaymentUtil.createOrderId(1);
        Assertions.assertNotNull(orderId);
    }

    @Test
    public void testGetRefundIdByOrderId() {
        String orderId = PaymentUtil.createOrderId(1);
        Assertions.assertEquals("TK" + orderId, PaymentUtil.getRefundIdByOrderId(orderId));
    }
}
