package com.incarcloud.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 第三方支付工具类
 *
 * @author Aaric, created on 2019-11-06T17:55.
 * @version 1.2.0-SNAPSHOT
 */
public final class PaymentUtil {

    /**
     * 获取订单ID（18位）<br>
     * <i>不推荐使用，建议自定义规则</i>
     *
     * @return
     */
    public static String createOrderId() {
        return createOrderId(ThreadLocalRandom.current().nextInt(9999));
    }

    /**
     * 获取订单ID（18位）
     *
     * @param uniqueIndex 唯一索引种子（4位）
     * @return
     */
    public static String createOrderId(Integer uniqueIndex) {
        return MessageFormat.format("{0}{1,number,0000}", DateFormatUtils.format(Date.from(Instant.now()),
                "yyyyMMddHHmmss"), uniqueIndex);
    }

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
