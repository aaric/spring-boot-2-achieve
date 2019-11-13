package com.incarcloud.log.service;

import com.incarcloud.pojo.entity.Log;

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
}
