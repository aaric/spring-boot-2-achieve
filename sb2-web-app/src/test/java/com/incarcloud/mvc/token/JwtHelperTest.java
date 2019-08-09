package com.incarcloud.mvc.token;

import com.incarcloud.sb2.App;
import org.junit.Assert;
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

    @Test
    public void testCreateToken() {
        String token = jwtHelper.createToken("abc", 1000);
        System.out.println(token);

        Assert.assertNotNull(token);
    }

    @Test
    public void testValidateToken() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhdXRoIiwiaXNzIjoiaW5jYXJjbG91ZCIsImlhdCI6MTU2NTMyMzA0NiwiYXVkIjoiMTAwMCIsIm5iZiI6MTU2NTMyMzA0NiwiZXhwIjoxNTY2NTMyNjQ2fQ.9UyuE3smGfwd3u6A_UJ0I0jNhjOuO5QZ0OyYmZ3TyRc";
        System.out.println(jwtHelper.validateToken(token));
    }
}
