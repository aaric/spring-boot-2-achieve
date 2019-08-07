package com.incarcloud.mvc.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * JwtTest
 *
 * @author Aaric, created on 2019-08-07T17:53.
 * @since 0.7.0-SNAPSHOT
 */
public class JwtTest {

    @Test
    public void testGetToken() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = Jwts.builder().setSubject("admin").signWith(key).compact();
        System.out.println("token:" + token);
        Assert.assertNotNull(token);
    }
}
