package com.incarcloud.mvc.token;

import com.incarcloud.sb2.App;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JwtHelperTest
 *
 * @author Aaric, created on 2019-08-09T11:24.
 * @since 0.7.0-SNAPSHOT
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class JwtHelperTest {

    @Autowired
    protected JwtHelper jwtHelper;

    private String token;

    @Before
    public void begin() {
        token = jwtHelper.createToken("cid", 1000);
        System.out.println(token);
    }

    @Test
    public void testCreateToken() {
        Assert.assertNotNull(token);
    }

    @Test
    public void testValidateToken() throws Exception {
        Assert.assertTrue(jwtHelper.validateToken("cid", token));
    }
}
