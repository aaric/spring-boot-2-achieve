package io.sparrow.sb2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 付款信息DTO
 *
 * @author Aaric, created on 2019-11-07T13:39.
 * @version 1.2.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "付款信息")
public class PaymentDto {

    /**
     * 订单ID
     */
    @ApiModelProperty(position = 1, value = "订单ID", example = "201911061645520001", required = true)
    private String orderId;

    /**
     * 付款内容（第三方支付），与付款数据二者选一
     */
    @ApiModelProperty(position = 2, value = "付款内容，与付款数据二者选一", required = true)
    private String content;

    /**
     * 付款数据（第三方支付），与付款内容二者选一
     */
    @ApiModelProperty(position = 3, value = "付款数据，与付款内容二者选一", required = true)
    private Map<String, String> data;

    public PaymentDto(String orderId, String content) {
        this.orderId = orderId;
        this.content = content;
    }

    public PaymentDto(String orderId, Map<String, String> data) {
        this.orderId = orderId;
        this.data = data;
    }
}
