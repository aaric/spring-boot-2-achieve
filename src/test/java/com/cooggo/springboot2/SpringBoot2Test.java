package com.cooggo.springboot2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot2Test
 *
 * @author Aaric, created on 2019-03-26T16:51.
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBoot2Test {

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Test
    public void testHello() {
        Assert.assertEquals("dev", springProfilesActive);
    }
}
