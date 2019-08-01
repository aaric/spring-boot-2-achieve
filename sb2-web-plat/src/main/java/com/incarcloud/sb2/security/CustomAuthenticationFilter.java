package com.incarcloud.sb2.security;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义用户认证过滤器
 *
 * @author Aaric, created on 2019-07-31T15:31.
 * @since 0.6.0-SNAPSHOT
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!StringUtils.endsWithIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE, request.getContentType())) {
            throw new AuthenticationServiceException("Authentication media type not supported: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest = null;
        try(InputStream input = request.getInputStream()) {
            LoginUserInfo loginUserInfo = JSON.parseObject(IOUtils.toString(input), LoginUserInfo.class);
            System.out.println(loginUserInfo);
            authRequest = new UsernamePasswordAuthenticationToken(loginUserInfo.getU(), loginUserInfo.getP());

        } catch (IOException e) {
            e.printStackTrace();
            authRequest = new UsernamePasswordAuthenticationToken("", "");

        } finally {
            setDetails(request, authRequest);
            return getAuthenticationManager().authenticate(authRequest);
        }
    }
}
