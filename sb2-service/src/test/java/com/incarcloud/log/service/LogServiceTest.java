package com.incarcloud.log.service;

import com.incarcloud.pojo.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * LogServiceTest
 *
 * @author Aaric, created on 2019-11-08T17:51.
 * @version 1.3.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    @Ignore
    public void testSaveLog() {
        Log log = new Log();
        log.setTag("plat");
        log.setTitle("test2");
        log.setContent("test contest2");
        Assert.assertTrue(logService.saveLog(log));
    }
}
