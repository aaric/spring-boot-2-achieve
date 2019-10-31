package com.incarcloud.common.pay;

/**
 * 微信支付服务接口
 *
 * @author Aaric, created on 2019-10-29T13:41.
 * @version 1.2.0-SNAPSHOT
 */
public interface WxPayService {

    /**
     * 创建订单
     *
     * @param productId   商品ID
     * @param orderId     订单ID
     * @param orderName   订单名称
     * @param totalAmount 订单总金额，单位：元
     * @param clientIp    终端IP
     * @return 交易编号，trade_no
     */
    String createOrder(String productId, String orderId, String orderName, float totalAmount, String clientIp) throws Exception;
}
