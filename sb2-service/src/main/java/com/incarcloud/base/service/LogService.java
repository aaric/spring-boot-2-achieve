package com.incarcloud.base.service;

import com.incarcloud.pojo.entity.Log;

import java.sql.Timestamp;

/**
 * 日志记录服务接口
 *
 * @author Aaric, created on 2019-11-08T17:49.
 * @version 1.3.0-SNAPSHOT
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param log 日志对象
     * @return
     */
    boolean saveLog(Log log);

    /**
     * 保存日志
     *
     * @param tagName            标签，即系统名称
     * @param title              标题，即模块名称
     * @param content            日志内容
     * @param submit             提交者，即用户名
     * @param serverProcessStart 服务端处理业务开始时间
     * @return
     */
    boolean saveLog(String tagName, String title, String content, String submit, Timestamp serverProcessStart);
}
