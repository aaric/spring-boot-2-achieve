package com.incarcloud.common.push;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JPushServiceTest
 *
 * @author Aaric, created on 2019-09-04T15:53.
 * @since 0.12.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JPushServiceTest {

    @Autowired
    protected JPushService jPushService;

    @Test
    @Ignore
    public void testPushToAndroid() {
        Long msgId = jPushService.pushToAndroid("title", "content", null, "8c46788b6fceef3f2897b9001c43db55");
        System.out.println(msgId);
    }
}
