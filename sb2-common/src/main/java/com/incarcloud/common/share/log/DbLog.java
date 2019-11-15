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
     * 客户端请求时间戳Key
     */
    String DEFAULT_CLIENT_TIME_KEY = "ts";

    /**
     * 记录开始请求时间戳Key
     */
    String DEFAULT_VISIT_START_KEY = "db-log-visit-start-key";

    /**
     * 日志内容格式化内容对象Key
     */
    String DEFAULT_CONTENT_OBJECTS_KEY = "db-log-content-objects-key";

    /**
     * 异常信息内容内容对象Key
     */
    String DEFAULT_EXCEPTION_DETAIL_KEY = "db-log-exception-detail-key";

    /**
     * 标签，即系统名称
     *
     * @return string
     */
    String tag() default "";

    /**
     * 分组名称，default-操作日志
     *
     * @return string
     */
    String classify() default "default";

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
     * @return string
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
     * @return string
     */
    String httpUrl() default "";

    /**
     * HTTP请求状态码
     *
     * @return int
     */
    int httpStatus() default 200;

    /**
     * HTTP请求类型
     *
     * @return string
     */
    String httpType() default "";

    /**
     * 客户端建立连接耗时
     *
     * @return long
     */
    long clientConnectInterval() default -1;

    /**
     * 服务端处理业务耗时
     *
     * @return long
     */
    long serverProcessInterval() default -1;

    /**
     * 异常信息，方便排除问题
     *
     * @return string
     */
    String exceptionDetail() default "";
}
