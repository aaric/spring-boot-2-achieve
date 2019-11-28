package com.incarcloud.base.service;

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
 * LogServiceTest
 *
 * @author Aaric, created on 2019-11-08T17:51.
 * @version 1.3.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    @Disabled
    public void testSaveLog() {
        Log log = new Log();
        log.setTagName("plat");
        log.setTitle("test2");
        log.setContent("test contest2");
        log.setServerProcessStart(Timestamp.valueOf(LocalDateTime.now()));
        Assertions.assertTrue(logService.saveLog(log));
    }

    @Test
    @Disabled
    public void testSaveLog2() {
        Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
        Assertions.assertTrue(logService.saveLog("plat", "test3", "test contest3", "username", startTime));
    }
}
