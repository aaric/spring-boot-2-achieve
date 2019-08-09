package com.incarcloud.mvc.token;

import com.incarcloud.common.exception.ApiException;
import com.incarcloud.common.share.Constant;
import com.incarcloud.mvc.token.settings.AuthJwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

/**
 * JWT辅助类
 *
 * @author Aaric, created on 2019-08-07T17:43.
 * @since 0.7.0-SNAPSHOT
 */
@Component
public class JwtHelper {

    /**
     * 授权使用场景
     */
    private static final String DEFAULT_JWT_SUBJECT = "auth";

    /**
     * 签发机构
     */
    private static final String DEFAULT_JWT_ISSUER = Constant.DEFAULT_ENTERPRISE_CODE;

    @Autowired
    private AuthJwtProperties authJwtProperties;

    /**
     * 创建Token字符串
     *
     * @param clientId 客户端ID
     * @param uid      授权ID，即用户ID
     * @return
     */
    public String createToken(String clientId, Integer uid) {
        byte[] secretKeyBytes = Base64.getDecoder().decode(authJwtProperties.getSecretKey());
        Key secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
        LocalDateTime expiredTime = LocalDateTime.now().plusSeconds(authJwtProperties.getTokenLeaseSeconds());
        return Jwts.builder().setId(clientId) //JWT ID
                .setSubject(DEFAULT_JWT_SUBJECT) //主题
                .setIssuer(DEFAULT_JWT_ISSUER) //签发机构
                .setIssuedAt(Date.from(Instant.now())) //签发时间，当前时间
                .setAudience(String.valueOf(uid)) //使用者
                .setNotBefore(Date.from(Instant.now())) //生效时间，立即生效
                .setExpiration(Date.from(expiredTime.toInstant(OffsetDateTime.now().getOffset()))) //过期时间
                .signWith(secretKey)
                .compact();
    }

    /**
     * 验证Token字符串
     *
     * @param token Token字符串
     * @return
     * @throws Exception
     */
    public boolean validateToken(String token) throws ApiException {
        try {
            // 解析Token信息
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(authJwtProperties.getSecretKey())).parseClaimsJws(token);
            System.out.println(claimsJws);

            // 验证授权场景

            // 验证签发机构

            return true;

        } catch (MalformedJwtException e) {
            // Token错误
        } catch (ExpiredJwtException e) {
            // Token已经失效
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
