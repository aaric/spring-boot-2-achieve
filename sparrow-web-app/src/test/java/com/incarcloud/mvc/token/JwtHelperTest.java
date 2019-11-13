package com.incarcloud.mvc.token;

import io.sparrow.sb2.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * JwtHelperTest
 *
 * @author Aaric, created on 2019-08-09T11:24.
 * @version 0.7.0-SNAPSHOT
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
public class JwtHelperTest {

    @Autowired
    protected JwtHelper jwtHelper;

    private String token;

    @BeforeEach
    public void begin() {
        token = jwtHelper.createToken("cid", 1000);
    }

    @Test
    public void testCreateToken() {
        log.info("token: " + token);
        Assertions.assertNotNull(token);
    }

    @Test
    public void testValidateToken() throws Exception {
        Assertions.assertTrue(jwtHelper.validateToken("cid", token));
    }
}
