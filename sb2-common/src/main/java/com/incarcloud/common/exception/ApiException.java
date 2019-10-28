package com.incarcloud.common.exception;

/**
 * 自定义请求API异常
 *
 * @author Aaric, created on 2019-08-06T10:18.
 * @version 0.6.3-SNAPSHOT
 */
public class ApiException extends Exception {

    /**
     * 状态码(0000-请求成功，其它异常)
     */
    private String code;

    /**
     * 附加信息
     */
    private String message;

    /**
     * 数据结果
     */
    private Object data;

    public ApiException(String code) {
        this.code = code;
    }

    public ApiException(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ApiException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
