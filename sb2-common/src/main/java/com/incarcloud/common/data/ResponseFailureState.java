package com.incarcloud.common.data;

/**
 * 请求响应数据失败状态码
 *
 * @author Aaric, created on 2019-08-05T19:27.
 * @since 0.6.3-SNAPSHOT
 */
public interface ResponseFailureState {

    /**
     * 0001 - 0020: 定义系统错误吗
     */
    // 非法请求
    String ERROR_0001 = "0001";

    // 系统繁忙，请稍候重试
    String ERROR_0002 = "0002";

    // 未知错误
    String ERROR_0003 = "0003";

    /**
     * 0021 - 0030: 定义数据校验错误码
     */
    // 数据校验失败
    String ERROR_0021 = "0021";

    /**
     * 0031 - 0050: 定义授权错误码
     */
    // 用户未登录
    String ERROR_0031 = "0031";

    // 权限不足
    String ERROR_0032 = "0032";

    // 用户名不存在
    String ERROR_0033 = "0033";

    // 用户名或密码错误
    String ERROR_0034 = "0034";

    // Token字符串错误
    String ERROR_0035 = "0035";

    // Token已过期
    String ERROR_0036 = "0036";

    // Token绑定错误
    String ERROR_0037 = "0037";

    // Token授权场景错误
    String ERROR_0038 = "0038";

    // Token签发机构错误
    String ERROR_0039 = "0039";
}
