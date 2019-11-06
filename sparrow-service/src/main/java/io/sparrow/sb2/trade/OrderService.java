package io.sparrow.sb2.trade;

/**
 * 订单管理服务接口
 *
 * @author Aaric, created on 2019-10-29T15:57.
 * @version 1.2.0-SNAPSHOT
 */
public interface OrderService {

    /**
     * 创建支付宝订单（电脑网站支付）
     *
     * @param goodsId 商品ID
     * @return 表单HTML
     */
    String createAliWebOrder(Integer goodsId) throws Exception;

    /**
     * 创建微信支付订单（电脑网站支付）
     *
     * @param goodsId  商品ID
     * @param clientIp 终端IP
     * @return 二维码地址
     */
    String createWxWebOrder(Integer goodsId, String clientIp) throws Exception;
}
