package io.sparrow.sb2.trade.controller;

import io.sparrow.sb2.trade.api.OrderApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理模块控制器
 *
 * @author Aaric, created on 2019-10-29T16:02.
 * @version 1.2.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/plat/trade/order")
public class OrderController implements OrderApi {

}
