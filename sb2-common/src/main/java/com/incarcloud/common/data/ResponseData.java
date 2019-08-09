package com.incarcloud.common.data;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 请求响应数据对象
 *
 * @author Aaric, created on 2019-08-05T19:21.
 * @since 0.6.3-SNAPSHOT
 */
public final class ResponseData<T> implements ResponseSuccessState, ResponseFailureState {

    @ApiModelProperty(value = "状态码(0000-请求成功，其它异常)")
    private String code;

    @ApiModelProperty(value = "附加信息")
    private String message;

    @ApiModelProperty(value = "数据结果")
    private T data;

    private ResponseData() {
    }

    private ResponseData(String code, T data) {
        this.code = code;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> ok() {
        return ok(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> ok(T data) {
        return new ResponseData(DEFAULT_SUCCESS, data);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> error(String errorCode) {
        return error(errorCode, null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> ResponseData<T> error(String errorCode, T data) {
        return new ResponseData(errorCode, data);
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> ResponseData<T> extraMsg(String msg) {
        this.setMessage(msg);
        return (ResponseData<T>) this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
