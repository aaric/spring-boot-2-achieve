package com.incarcloud.common.msg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * EmailServiceTest
 *
 * @author Aaric, created on 2019-08-22T17:03.
 * @version 0.8.1-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmailServiceTest {

    @Autowired(required = false)
    private EmailService emailService;

    @Test
    @Disabled
    public void testSendTemplate() {
        Assertions.assertTrue(emailService.sendTemplate("Hello", "<h1>You are my super man.</h1>", "acccount@incarcloud.com"));
    }
}
