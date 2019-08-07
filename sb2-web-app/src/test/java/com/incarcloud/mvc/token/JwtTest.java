package com.incarcloud.mvc.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;

/**
 * JwtTest
 *
 * @author Aaric, created on 2019-08-07T17:53.
 * @since 0.7.0-SNAPSHOT
 */
public class JwtTest {

    @Test
    public void testGetToken() throws Exception {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("key: " + Base64.getEncoder().encodeToString(key.getEncoded()));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String token = Jwts.builder().setSubject("admin").setExpiration(dateFormat.parse("2019-10-01 00:00:00")).signWith(key).compact();
        System.out.println("token: " + token);

        Assert.assertNotNull(token);
    }

    @Test
    public void testCheckToken() {
        String base64Key = "P96kjPrQbE9dixFDcerMrz8oBb+X04QkWypHhNIJ0r0=";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2OTg1OTIwMH0._vng9i3pyXfAOgC2E1uST1h3xUyANvS0H6eF3sPgT58";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(base64Key)).parseClaimsJws(token);
        System.out.println(claimsJws);

        Assert.assertEquals("admin", claimsJws.getBody().get("sub"));
    }
}
