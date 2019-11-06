package io.sparrow.sb2.trade.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * 第三方支付回调接口
 *
 * @author Aaric, created on 2019-10-29T16:10.
 * @version 1.2.0-SNAPSHOT
 */
@Api(tags = "第三方支付回调接口API")
public interface CallbackApi {

    @ApiOperation(value = "微信支付结果")
    WXPayResult wxpay();

    @ApiOperation(value = "阿里支付结果")
    Map<String, String> alipay();

    /**
     * 第三方支付结果回调响应结果对象
     *
     * @author Aaric, created on 2019-10-29T16:13.
     * @version 1.2.0-SNAPSHOT
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @JacksonXmlRootElement(localName = "xml")
    class WXPayResult {

        @Setter
        @Getter
        @JacksonXmlProperty(localName = "return_code")
        private String returnCode;

        @Setter
        @Getter
        @JacksonXmlProperty(localName = "return_msg")
        private String returnMsg;
    }
}
