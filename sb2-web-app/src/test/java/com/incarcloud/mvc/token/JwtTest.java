package com.incarcloud.mvc.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

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
        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString()) //JWT ID
                .setSubject("test") //主题
                .setIssuer("test") //签发机构
                .setIssuedAt(Date.from(Instant.now())) //签发时间，当前时间
                .setAudience("admin") //使用者
                .setNotBefore(Date.from(Instant.now())) //生效时间，立即生效
                .setExpiration(dateFormat.parse("2025-03-31 00:00:00")) //过期时间
                .signWith(key).compact();
        System.out.println("token: " + token);

        Assert.assertNotNull(token);
    }

    @Test
    public void testCheckToken() {
        String base64Key = "gYYk2SsjEAd4p/CgCQgIpei4BI9lVctYB/dnVkZH6Uw=";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwZjk2YzRkZC01ZDRkLTQ1MzgtYWFiYi0xZjQ0MzExZWM0YzMiLCJzdWIiOiJ0ZXN0IiwiaXNzIjoidGVzdCIsImlhdCI6MTU2NTMyNjQ4MSwiYXVkIjoiYWRtaW4iLCJuYmYiOjE1NjUzMjY0ODEsImV4cCI6MTc0MzM1MDQwMH0.XC5s3HNxC0_oNJMu8HrkTkNvbu4WWpHyV2w8per0iJI";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(base64Key)).parseClaimsJws(token);
        // header={alg=HS256},body={jti=0f96c4dd-5d4d-4538-aabb-1f44311ec4c3, sub=test, iss=test, iat=1565326481, aud=admin, nbf=1565326481, exp=1743350400},signature=XC5s3HNxC0_oNJMu8HrkTkNvbu4WWpHyV2w8per0iJI
        System.out.println(claimsJws);

        Assert.assertEquals("admin", claimsJws.getBody().getAudience());
    }
}
