package com.incarcloud.mvc.security.filter;

import com.alibaba.fastjson.JSON;
import com.incarcloud.mvc.security.entity.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
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
 * 自定义JSON认证过滤器
 *
 * @author Aaric, created on 2019-07-31T15:31.
 * @since 0.6.0-SNAPSHOT
 */
public class CustomJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!(StringUtils.endsWithIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE, request.getContentType())
                || StringUtils.endsWithIgnoreCase(MediaType.APPLICATION_JSON_VALUE, request.getContentType()))) {
            throw new AuthenticationServiceException("Authentication media type not supported: " + request.getContentType());
        }

        Authentication authentication;
        UsernamePasswordAuthenticationToken authRequest = null;
        try (InputStream input = request.getInputStream()) {
            LoginUserInfo loginUserInfo = JSON.parseObject(IOUtils.toString(input), LoginUserInfo.class);
            authRequest = new UsernamePasswordAuthenticationToken(loginUserInfo.getU(), loginUserInfo.getP());

        } catch (IOException e) {
            authRequest = new UsernamePasswordAuthenticationToken("", "");
            logger.error("attemptAuthentication error", e);

        } finally {
            setDetails(request, authRequest);
            authentication = getAuthenticationManager().authenticate(authRequest);
        }
        return authentication;
    }
}
