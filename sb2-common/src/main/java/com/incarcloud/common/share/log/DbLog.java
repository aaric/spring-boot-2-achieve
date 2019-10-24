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
     * 记录开始请求时间戳Key
     */
    String DEFAULT_VISIT_START_KEY = "db-log-visit-start-key";

    /**
     * 当前登录用户名对象Key
     */
    String DEFAULT_CURRENT_UID_KEY = "db-log-current-uid-key";

    /**
     * 日志内容格式化内容对象Key
     */
    String DEFAULT_CONTENT_OBJECTS_KEY = "db-log-content-objects-key";

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
     * 日志支持MessageFormat格式化内容
     *
     * @return
     */
    String contentObjectsKey() default DEFAULT_CONTENT_OBJECTS_KEY;

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
     * HTTP请求地址
     *
     * @return int string
     */
    String httpUrl() default "";

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
    long httpInterval() default -1;

    /**
     * 处理请求异常信息
     *
     * @return string
     */
    String exceptionDetail() default "";
}
