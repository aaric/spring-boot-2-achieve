package io.sparrow.sb2.trade.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.incarcloud.common.config.settings.AliPayProperties;
import io.sparrow.sb2.trade.api.CallbackApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方支付回调接口实现
 *
 * @author Aaric, created on 2019-10-29T16:13.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/plat/trade/callback")
public class CallbackController implements CallbackApi {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Override
    @PostMapping(value = "/alipay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String alipay(HttpServletRequest request) {
        // 获得应用ID和订单ID
        String appId = request.getParameter("app_id");
        String orderId = request.getParameter("orderId");
        log.debug("alipay callbak -> appId: {}, orderId: {}", appId, orderId);

        // 封装全部回调信息到map对象
        Map<String, String> validateParams = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();
        params.forEach((key, values) -> {
            log.debug("key: {}, values:{}", key, values);
            validateParams.put(key, values[0]);
        });

        // 调用SDK验证签名
        try {
            // 查询签名校验状态
            boolean signVerified = AlipaySignature.rsaCheckV1(validateParams,
                    aliPayProperties.getAlipayPublicKey(), AliPayProperties.DEFAULT_API_CHARSET);
            log.debug("signVerified: {}", signVerified);
            if (signVerified) {
                // 支付成功，请刷新订单状态
                log.info("TODO: 支付宝支付成功， 请刷新订单ID（{}）状态...", orderId);
            }

        } catch (AlipayApiException e) {
            // 校验支付宝签名失败
            log.error("alipay error", e);
            return "failure";
        }

        // 返回string响应结果(or "failure")
        return "success";
    }

    /**
     * 获得微信支付回调XML字符串
     *
     * @param request 请求对象
     * @return 字符串
     */
    private String getWxXMLString(HttpServletRequest request) {
        try (InputStream input = request.getInputStream()) {
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    output.write(buffer, 0, len);
                }
                return new String(output.toByteArray(), "utf-8");
            }
        } catch (IOException e) {
            log.error("getXMLString error", e);
        }
        return null;
    }

    @Override
    @PostMapping(value = "/wxpay", produces = MediaType.APPLICATION_XML_VALUE)
    public WXPayResult wxpay(HttpServletRequest request) {
        // 获得XML结果
        String xml = getWxXMLString(request);
        log.debug("xml: {}", xml);

        // 解析XML为对象
        try {
            // 判断结果数据
            if (StringUtils.isNotBlank(xml)) {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.setDefaultUseWrapper(false);
                xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                WXPayData response = xmlMapper.readValue(xml, WXPayData.class);
                if (null != response && "SUCCESS".equals(response.getReturnCode())) {
                    // 支付成功，请刷新订单状态
                    log.info("TODO: 微信支付成功， 请刷新订单ID（{}）状态...", response.getOrderId());
                }
            }
        } catch (IOException e) {
            // xml转换对象失败
            log.error("wxpay error", e);
        }

        // 返回xml响应结果
        return new WXPayResult("SUCCESS", "OK");
    }
}
