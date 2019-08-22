package com.incarcloud.common.msg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * EmailServiceTest
 *
 * @author Aaric, created on 2019-08-22T17:03.
 * @since 0.8.1-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    @Ignore
    public void testSendTemplate() {
        Assert.assertTrue(emailService.sendTemplate("Hello", "<h1>You are my super man.</h1>", "acccount@incarcloud.com"));
    }
}
