package com.incarcloud.sb2.security;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

        System.out.println("------->" + request.getContentType());

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

    static class LoginUserInfo {
        private Long id;
        private String u;
        private String p;
        private Long ts;
        private String v;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getU() {
            return u;
        }

        public void setU(String u) {
            this.u = u;
        }

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public Long getTs() {
            return ts;
        }

        public void setTs(Long ts) {
            this.ts = ts;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
