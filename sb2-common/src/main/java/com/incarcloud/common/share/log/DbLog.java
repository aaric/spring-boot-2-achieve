package com.incarcloud.common.share.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扫描日志注解类
 *
 * @author Aaric, created on 2019-10-11T13:43.
 * @since 1.1.0-SNAPSHOT
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbLog {

    /**
     * 模块名称
     *
     * @return string
     */
    String module();

    /**
     * 日志标题
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
     * 提交者
     *
     * @return string
     */
    String submit();

    /**
     * 备注
     *
     * @return string
     */
    String remark() default "";

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
    String exceptionDetail();
}
