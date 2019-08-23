package com.incarcloud.common.sms;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * AliyunSmsServiceTest
 *
 * @author Aaric, created on 2019-08-23T15:21.
 * @since 0.10.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AliyunSmsServiceTest {

    @Autowired
    protected AliyunSmsService aliyunSmsService;

    @Test
    @Ignore
    public void testSendTemplate() {
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("code", MessageFormat.format("{0,number,000000}", new Random().nextInt(999999)));

        String bizId = aliyunSmsService.sendTemplate("SMS_150740230", templateParams, null, "1340000xxxx");
        log.info(bizId);
        Assert.assertNotNull(bizId);
    }
}
