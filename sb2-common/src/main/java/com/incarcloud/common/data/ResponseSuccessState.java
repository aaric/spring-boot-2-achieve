package com.incarcloud.common.data;

/**
 * 请求响应数据成功状态码
 *
 * @author Aaric, created on 2019-08-05T19:25.
 * @since 0.6.3-SNAPSHOT
 */
public interface ResponseSuccessState {

    /**
     * 请求成功
     */
    String DEFAULT_SUCCESS = "0000";

    /**
     * 获得状态码字符串
     *
     * @return
     */
    String getCode();
}
