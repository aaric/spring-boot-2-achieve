package com.incarcloud.common.pay;

/**
 * 支付宝服务接口
 *
 * @author Aaric, created on 2019-10-30T16:00.
 * @version 1.2.0-SNAPSHOT
 */
public interface AliPayService {

    /**
     * 创建订单
     *
     * @param orderId        订单ID
     * @param goodsCode      商品编号
     * @param totalAmount    订单总金额，单位：元
     * @param goodsName      商品名称
     * @param goodsDesc      商品名称
     * @param refId          业务ID，可选
     * @param passbackParams 公用回传参数，可选
     * @return 立即支付表单HTML
     * @throws Exception
     */
    String createOrder(String orderId, String goodsCode, float totalAmount, String goodsName, String goodsDesc,
                       String refId, String passbackParams) throws Exception;
}
