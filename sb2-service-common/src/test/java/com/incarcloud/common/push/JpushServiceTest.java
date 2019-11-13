package com.incarcloud.common.push;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * JpushServiceTest
 *
 * @author Aaric, created on 2019-09-04T15:53.
 * @version 0.12.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JpushServiceTest {

    @Autowired(required = false)
    protected JpushService jpushService;

    @Test
    @Disabled
    public void testPushToAndroid() {
        Long msgId = jpushService.pushToAndroid("title", "content", null, "8c46788b6fceef3f2897b9001c43db55");
        log.info("msgId: {}", msgId);
        Assertions.assertNotNull(msgId);
    }

    @Test
    @Disabled
    public void testPushToApple() {
        Long msgId = jpushService.pushToApple("title", "content", null, "ED28AF6401DD4C0C986F9E26EA372006");
        log.info("msgId: {}", msgId);
        Assertions.assertNotNull(msgId);
    }
}
