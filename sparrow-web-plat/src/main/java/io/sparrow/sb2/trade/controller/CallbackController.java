package io.sparrow.sb2.trade.controller;

import io.sparrow.sb2.trade.api.CallbackApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三方支付回调接口实现
 *
 * @author Aaric, created on 2019-10-29T16:13.
 * @version 1.2.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/plat/trade/callback")
public class CallbackController implements CallbackApi {

    @Override
    @PostMapping(value = "/wxpay", produces = MediaType.APPLICATION_XML_VALUE)
    public WXPayResult wxpay() {
        return new WXPayResult("SUCCESS", "OK");
    }

    @Override
    @PostMapping(value = "/alipay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> alipay() {
        Map<String, String> result = new HashMap<>();
        result.put("return_code", "SUCCESS");
        result.put("return_msg", "OK");
        return result;
    }
}
