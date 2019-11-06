package io.sparrow.sb2.trade.impl;

import com.incarcloud.common.pay.AliPayService;
import com.incarcloud.common.pay.WxPayService;
import io.sparrow.sb2.trade.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * 订单管理服务实现
 *
 * @author Aaric, created on 2019-10-29T15:58.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 支付宝
     */
    @Autowired
    private AliPayService aliPayService;

    /**
     * 微信支付
     */
    @Autowired
    private WxPayService wxPayService;

    /**
     * 时间格式转换
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public String createAliWebOrder(Integer goodsId) throws Exception {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给AliPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = MessageFormat.format("{0}0001", dateFormat.format(Date.from(Instant.now())));

        // 支付宝
        String html = aliPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F, "支付宝测试商品", "支付宝测试描述");
        log.info("html: {}", html);

        return html;
    }

    @Override
    public String createWxWebOrder(Integer goodsId, String clientIp) throws Exception {
        // 打印说明信息
        log.info("TODO: 需要根据商品ID查询详细商品信息，然后传递给WxPayService进行支付动作");
        log.info("create web order: {}", goodsId);

        // 创建订单ID
        String orderId = MessageFormat.format("{0}0001", dateFormat.format(Date.from(Instant.now())));

        // 微信支付
        String qrCodeUrl = wxPayService.createWebOrder(orderId, "FAST_INSTANT_TRADE_PAY", 0.01F, "支付宝测试商品", "支付宝测试描述", clientIp);
        log.info("qcCodeUrl: {}", qrCodeUrl);

        return qrCodeUrl;
    }
}
