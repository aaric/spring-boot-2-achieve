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
                .setSubject("App Login") //主题
                .setIssuer("英卡科技") //签发机构
                .setIssuedAt(Date.from(Instant.now())) //签发时间，当前时间
                .setAudience("admin") //使用者
                .setNotBefore(Date.from(Instant.now())) //生效时间，立即生效
                .setExpiration(dateFormat.parse("2019-10-01 00:00:00")) //过期时间
                .signWith(key).compact();
        System.out.println("token: " + token);

        Assert.assertNotNull(token);
    }

    @Test
    public void testCheckToken() {
        String base64Key = "4R2edylSVUUxuc6bFhUT2tN0VxUKbu7kETtoI1mCbXg=";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxYzcxMzZhYS04MDExLTRlZDMtYWQyMi0wOTQ4ZWQyNzkxMDUiLCJzdWIiOiJBcHAgTG9naW4iLCJpc3MiOiLoi7HljaHnp5HmioAiLCJpYXQiOjE1NjUzMTkwMDcsImF1ZCI6ImFkbWluIiwibmJmIjoxNTY1MzE5MDA3LCJleHAiOjE1Njk4NTkyMDB9.ujoNlHx-uBNieQBy-3souD_5YIYO-8TcWMtPlamSKVA";
        // header={alg=HS256},body={jti=1c7136aa-8011-4ed3-ad22-0948ed279105, sub=App Login, iss=英卡科技, iat=1565319007, aud=admin, nbf=1565319007, exp=1569859200},signature=ujoNlHx-uBNieQBy-3souD_5YIYO-8TcWMtPlamSKVA
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(base64Key)).parseClaimsJws(token);
        System.out.println(claimsJws);

        Assert.assertEquals("admin", claimsJws.getBody().getAudience());
    }
}
