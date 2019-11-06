package io.sparrow.sb2.trade.controller;

import com.incarcloud.common.data.ResponseData;
import io.sparrow.sb2.trade.OrderService;
import io.sparrow.sb2.trade.api.OrderApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理模块控制器
 *
 * @author Aaric, created on 2019-10-29T16:02.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/plat/trade/order")
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/createOrder/{paymentType}/{goodsId}")
    public ResponseData<String> createOrder(@PathVariable Integer paymentType, @PathVariable Integer goodsId) throws Exception {
        log.info("goodsId: {}", goodsId);
        if (2 == paymentType) {
            // 微信支付
            return ResponseData.ok(orderService.createWxWebOrder(goodsId, "59.173.243.67"));
        } else {
            // 默认支付宝
            return ResponseData.ok(orderService.createAliWebOrder(goodsId));
        }
    }
}
