package io.sparrow.sb2.trade.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import io.sparrow.sb2.dto.PaymentDto;
import io.swagger.annotations.*;

/**
 * 订单管理模块API
 *
 * @author Aaric, created on 2019-11-07T16:29.
 * @version 1.2.1-SNAPSHOT
 */
@Api(tags = "订单管理模块API")
public interface OrderApi {

    @ApiOperation(value = "创建App支付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paymentType", value = "支付类型: 1-支付宝, 2-微信支付", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 61, message = "0061-调用支付宝接口失败"),
            @ApiResponse(code = 71, message = "0071-调用微信支付接口失败")
    })
    ResponseData<PaymentDto> createOrder(Integer paymentType, Integer goodsId) throws ApiException;
}
