package com.incarcloud.base.mapper;

import com.incarcloud.pojo.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * LogMapperTest
 *
 * @author Aaric, created on 2019-11-08T17:50.
 * @version 1.3.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LogMapperTest {

    @Autowired
    private LogMapper logMapper;

    @Test
    @Disabled
    public void testInsert() {
        Log log = new Log();
        log.setTag("plat");
        log.setTitle("test1");
        log.setContent("test contest1");
        log.setServerProcessStart(Timestamp.valueOf(LocalDateTime.now()));
        Assertions.assertNotEquals(0, logMapper.insert(log));
    }
}
