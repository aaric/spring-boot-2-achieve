package com.incarcloud.common.pay;

import com.incarcloud.common.exception.ApiException;

/**
 * 微信支付服务接口
 *
 * @author Aaric, created on 2019-10-29T13:41.
 * @version 1.2.0-SNAPSHOT
 */
public interface WxPayService {

    /**
     * 创建订单（电脑网站支付）
     *
     * @param orderId     订单ID
     * @param goodsCode   商品编号，建议使用商品条形码编号
     * @param totalAmount 订单总金额，单位：元
     * @param goodsName   商品名称
     * @param goodsDesc   商品名称
     * @param clientIp    终端IP
     * @return 二维码地址
     */
    String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                          String goodsDesc, String clientIp) throws ApiException;

    /**
     * 查询订单支付状态
     *
     * @param orderId 订单ID
     * @return 订单状态
     * @throws ApiException
     */
    boolean queryOrderStatus(String orderId) throws ApiException;

    /**
     * 订单退款
     *
     * @param orderId      订单ID
     * @param refundAmount 退款金额，单位：元
     * @param refundId     退款请求ID，用于查询退款状态，可选
     * @return 退款状态
     * @throws ApiException
     */
    boolean refundOrder(String orderId, float refundAmount, String refundId) throws ApiException;

    /**
     * 查询订单退款状态
     *
     * @param orderId  订单ID
     * @param refundId 退款请求ID，可选，与支付宝保持统一
     * @return 退款状态
     * @throws ApiException
     */
    boolean queryRefundOrderStatus(String orderId, String refundId) throws ApiException;

    /**
     * 订单退款（退款ID='TK'+orderId）
     *
     * @param orderId      订单ID
     * @param refundAmount 退款金额，单位：元
     * @return 退款状态
     * @throws ApiException
     */
    boolean refundOrder(String orderId, float refundAmount) throws ApiException;

    /**
     * 查询订单退款状态（退款ID='TK'+orderId）
     *
     * @param orderId 订单ID
     * @return 退款状态
     * @throws ApiException
     */
    boolean queryRefundOrderStatus(String orderId) throws ApiException;
}
