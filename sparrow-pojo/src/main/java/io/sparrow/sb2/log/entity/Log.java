package io.sparrow.sb2.log.entity;

import lombok.Data;

/**
 * 日志信息
 *
 * @author Aaric, created on 2019-11-08T17:52.
 * @version 1.3.0-SNAPSHOT
 */
@Data
public class Log {

    /**
     * ID
     */
    private Integer id;

    /**
     * 标签，即系统名称
     */
    private String tag;

    /**
     * 标题，即模块名称
     */
    private String title;

    /**
     * 日志内容
     */
    private String content;
}
