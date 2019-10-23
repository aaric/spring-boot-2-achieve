package com.incarcloud.common.share.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扫描日志注解类
 *
 * @author Aaric, created on 2019-10-11T13:43.
 * @version 1.1.0-SNAPSHOT
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbLog {

    /**
     * 系统名称，可以自定义
     *
     * @return string
     */
    String tag() default "";

    /**
     * 模块名称，即标题
     *
     * @return string
     */
    String title() default "";

    /**
     * 日志内容
     *
     * @return string
     */
    String content();

    /**
     * 备注，附加信息
     *
     * @return string
     */
    String remark() default "";

    /**
     * 提交者，可以自定义
     *
     * @return string
     */
    String submit() default "";

    /**
     * HTTP状态码
     *
     * @return string
     */
    int httpStatus() default 200;

    /**
     * 响应时间间隔
     *
     * @return string
     */
    int responseInterval() default -1;

    /**
     * 异常信息
     *
     * @return string
     */
    String exceptionDetail() default "";
}
