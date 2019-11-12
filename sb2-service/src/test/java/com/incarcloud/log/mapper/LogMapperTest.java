package com.incarcloud.log.mapper;

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
 * LogMapperTest
 *
 * @author Aaric, created on 2019-11-08T17:50.
 * @version 1.3.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogMapperTest {

    @Autowired
    private LogMapper logMapper;

    @Test
    @Ignore
    public void testInsert() throws Exception {
        Log log = new Log();
        log.setTag("plat");
        log.setTitle("test1");
        log.setContent("test contest1");
        Assert.assertNotEquals(0, logMapper.insert(log));
    }
}
