package com.incarcloud.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incarcloud.log.mapper.LogMapper;
import com.incarcloud.log.service.LogService;
import com.incarcloud.pojo.entity.Log;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现
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
}
