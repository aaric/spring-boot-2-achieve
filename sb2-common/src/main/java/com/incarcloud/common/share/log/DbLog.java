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
     * 标签，即系统名称
     *
     * @return string
     */
    String tag() default "";

    /**
     * 标题，即模块名称
     *
     * @return string
     */
    String title();

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
     * @return int
     */
    int httpStatus() default 200;

    /**
     * HTTP类型
     *
     * @return string
     */
    String httpType() default "";

    /**
     * HTTP请求耗时
     *
     * @return int
     */
    int httpInterval() default -1;

    /**
     * 异常信息
     *
     * @return string
     */
    String exceptionDetail() default "";
}
