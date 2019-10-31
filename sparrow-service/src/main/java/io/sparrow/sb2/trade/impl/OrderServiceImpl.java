package io.sparrow.sb2.trade.impl;

import io.sparrow.sb2.trade.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单管理服务实现
 *
 * @author Aaric, created on 2019-10-29T15:58.
 * @version 1.2.0-SNAPSHOT
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public String sayHello() {
        return "hello world";
    }
}
