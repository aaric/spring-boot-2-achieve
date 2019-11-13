package io.sparrow.sb2.trade.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方支付回调接口
 *
 * @author Aaric, created on 2019-10-29T16:10.
 * @version 1.2.0-SNAPSHOT
 */
@Api(tags = "第三方支付回调接口API")
public interface CallbackApi {

    @ApiOperation(value = "阿里支付结果")
    String alipay(HttpServletRequest request);

    @ApiOperation(value = "微信支付结果")
    WXPayResult wxpay(HttpServletRequest request);

    /**
     * 第三方支付结果回调响应结果对象
     *
     * @author Aaric, created on 2019-10-29T16:13.
     * @version 1.2.1-SNAPSHOT
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @NoArgsConstructor
    @JacksonXmlRootElement(localName = "xml")
    class WXPayData extends WXPayResult {

        @JacksonXmlProperty(localName = "appid")
        private String appId;

        @JacksonXmlProperty(localName = "out_trade_no")
        private String orderId;
    }

    /**
     * 第三方支付结果回调响应结果对象
     *
     * @author Aaric, created on 2019-10-29T16:13.
     * @version 1.2.1-SNAPSHOT
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @NoArgsConstructor
    @JacksonXmlRootElement(localName = "xml")
    class WXPayResult {

        @JacksonXmlProperty(localName = "return_code")
        private String returnCode;

        @JacksonXmlProperty(localName = "return_msg")
        private String returnMsg;

        public WXPayResult(String returnCode, String returnMsg) {
            this.returnCode = returnCode;
            this.returnMsg = returnMsg;
        }
    }
}
