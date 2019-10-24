package com.incarcloud.common.share;

/**
 * 共享常量
 *
 * @author Aaric, created on 2019-07-16T11:06.
 * @version 0.4.2-SNAPSHOT
 */
public interface Constant {

    /**
     * 默认错误/异常代码提示前缀字符串
     */
    String DEFAULT_TIPS_CODE_PREV_STRING = "tips.default.error.";

    /**
     * 默认企业代码
     */
    String DEFAULT_ENTERPRISE_CODE = "incarcloud";

    /**
     * 默认域名前缀
     */
    String DEFAULT_DOMAIN_PREFIX = "com." + DEFAULT_ENTERPRISE_CODE;
}
