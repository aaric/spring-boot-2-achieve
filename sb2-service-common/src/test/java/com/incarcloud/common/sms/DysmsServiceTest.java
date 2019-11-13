package com.incarcloud.common.sms;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * AliyunSmsServiceTest
 *
 * @author Aaric, created on 2019-08-23T15:21.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DysmsServiceTest {

    @Autowired(required = false)
    protected DysmsService dysmsService;

    @Test
    @Disabled
    public void testSendTemplate() {
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("code", MessageFormat.format("{0,number,000000}", new Random().nextInt(999999)));

        String bizId = dysmsService.sendTemplate("SMS_150740230", templateParams, null, "1340000xxxx");
        log.info(bizId);
        Assertions.assertNotNull(bizId);
    }
}
