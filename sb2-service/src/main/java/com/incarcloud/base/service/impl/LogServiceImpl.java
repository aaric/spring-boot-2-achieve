package com.incarcloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incarcloud.base.mapper.LogMapper;
import com.incarcloud.base.service.LogService;
import com.incarcloud.pojo.entity.Log;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 日志记录服务实现
 *
 * @author Aaric, created on 2019-11-11T16:55.
 * @version 1.3.0-SNAPSHOT
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public boolean saveLog(Log log) {
        int flag = baseMapper.insert(log);
        if (0 < flag) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveLog(String tagName, String title, String content, String submit, Timestamp serverProcessStart) {
        Log log = new Log();
        log.setTagName(tagName);
        log.setTitle(title);
        log.setContent(content);
        log.setSubmit(submit);
        log.setServerProcessStart(serverProcessStart);
        return saveLog(log);
    }
}
