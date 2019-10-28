package com.incarcloud.common.share.log;

/**
 * 扫描日志日志提交人
 *
 * @author Aaric, created on 2019-10-28T13:56.
 * @version 1.1.0-SNAPSHOT
 */
public interface DbLogSubmit {

    /**
     * 获得提交人信息，即UID
     *
     * @return
     */
    String getSubmit();
}
