package com.incarcloud.common.pay;

import com.incarcloud.common.exception.ApiException;

/**
 * 支付宝服务接口
 *
 * @author Aaric, created on 2019-10-30T16:00.
 * @version 1.2.0-SNAPSHOT
 */
public interface AliPayService {

    /**
     * 创建订单（电脑网站支付）<br>
     * <code>
     * // 接收支付宝表单HTML后，前端建议处理方式
     * const aliPayDiv = document.createElement('div')
     * aliPayDiv.innerHTML = formHtml
     * document.body.appendChild(aliPayDiv)
     * document.forms[0].submit()
     * </code>
     *
     * @param orderId     订单ID
     * @param goodsCode   商品编号，建议使用商品条形码编号
     * @param totalAmount 订单总金额，单位：元
     * @param goodsName   商品名称
     * @param goodsDesc   商品名称
     * @return 支付表单HTML
     * @throws ApiException
     */
    String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                          String goodsDesc) throws ApiException;

    /**
     * 创建订单（电脑网站支付）<br>
     * <code>
     * // 接收支付宝表单HTML后，前端建议处理方式
     * const aliPayDiv = document.createElement('div')
     * aliPayDiv.innerHTML = formHtml
     * document.body.appendChild(aliPayDiv)
     * document.forms[0].submit()
     * </code>
     *
     * @param orderId        订单ID
     * @param goodsCode      商品编号，建议使用商品条形码编号
     * @param totalAmount    订单总金额，单位：元
     * @param goodsName      商品名称
     * @param goodsDesc      商品名称
     * @param refId          业务ID，可选
     * @param passbackParams 公用回传参数，可选
     * @return 支付表单HTML
     * @throws ApiException
     */
    String createWebOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                          String goodsDesc, String refId, String passbackParams) throws ApiException;

    /**
     * 创建订单（APP支付）
     *
     * @param orderId     订单ID
     * @param goodsCode   商品编号，建议使用商品条形码编号
     * @param totalAmount 订单总金额，单位：元
     * @param goodsName   商品名称
     * @param goodsDesc   商品名称
     * @return 调用SDK参数信息
     * @throws ApiException
     */
    String createAppOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                          String goodsDesc) throws ApiException;

    /**
     * 创建订单（APP支付）
     *
     * @param orderId        订单ID
     * @param goodsCode      商品编号，建议使用商品条形码编号
     * @param totalAmount    订单总金额，单位：元
     * @param goodsName      商品名称
     * @param goodsDesc      商品名称
     * @param refId          业务ID，可选
     * @param passbackParams 公用回传参数，可选
     * @return 调用SDK参数信息
     * @throws ApiException
     */
    String createAppOrder(String orderId, String goodsCode, float totalAmount, String goodsName,
                          String goodsDesc, String refId, String passbackParams) throws ApiException;

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
     * @param refundId 退款请求ID
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
