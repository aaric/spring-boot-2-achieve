package io.sparrow.sb2.trade.impl;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import io.sparrow.sb2.trade.api.OrderApi;
import io.sparrow.sb2.trade.OrderService;
import io.sparrow.sb2.trade.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理模块控制器
 *
 * @author Aaric, created on 2019-11-07T16:32.
 * @version 1.2.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/app/trade/order")
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/createOrder/{paymentType}/{goodsId}")
    public ResponseData<PaymentDto> createOrder(@PathVariable Integer paymentType, @PathVariable Integer goodsId) throws ApiException {
        log.info("goodsId: {}", goodsId);
        // 支付类型：1-支付宝，2-微信支付
        if (2 == paymentType) {
            // 微信支付
            return ResponseData.ok(orderService.createWxAppOrder(goodsId, "59.173.243.67"));
        } else {
            // 支付宝
            return ResponseData.ok(orderService.createAliAppOrder(goodsId));
        }
    }
}
