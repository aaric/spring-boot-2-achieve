package com.incarcloud.common.utils;

/**
 * 第三方支付工具类
 *
 * @author Aaric, created on 2019-11-06T17:55.
 * @version 1.2.0-SNAPSHOT
 */
public final class PaymentUtil {

    /**
     * 获得默认退款ID（规则：'TK'+orderId）
     *
     * @param orderId 订单ID
     * @return
     */
    public static String getRefundIdByOrderId(String orderId) {
        return "TK" + orderId;
    }
}
