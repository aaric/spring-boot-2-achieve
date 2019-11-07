package io.sparrow.sb2.trade;

import com.incarcloud.common.exception.ApiException;
import io.sparrow.sb2.trade.dto.PaymentDto;

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
     * @return 支付表单HTML
     */
    PaymentDto createAliWebOrder(Integer goodsId) throws ApiException;

    /**
     * 创建微信支付订单（电脑网站支付）
     *
     * @param goodsId  商品ID
     * @param clientIp 终端IP
     * @return 支付二维码URL
     */
    PaymentDto createWxWebOrder(Integer goodsId, String clientIp) throws ApiException;

    /**
     * 创建支付宝订单（APP支付）
     *
     * @param goodsId 商品ID
     * @return 支付表单HTML
     */
    PaymentDto createAliAppOrder(Integer goodsId) throws ApiException;

    /**
     * 创建微信支付订单（电脑网站支付）
     *
     * @param goodsId  商品ID
     * @param clientIp 终端IP
     * @return 支付二维码URL
     */
    PaymentDto createWxAppOrder(Integer goodsId, String clientIp) throws ApiException;
}
