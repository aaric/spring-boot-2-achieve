package com.incarcloud.mvc.token.filter;

import com.incarcloud.mvc.token.settings.AuthJwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT安全框架过滤器
 *
 * @author Aaric, created on 2019-08-08T11:29.
 * @since 0.7.0-SNAPSHOT
 */
@Component
@WebFilter(filterName = "bizWebJwtFilter", urlPatterns = "/api/app/**")
public class BizWebJwtFilter extends OncePerRequestFilter {

    /**
     * 默认授权登录路由，设置所有人都可以访问
     */
    private final static String DEFAULT_AUTH_ROUTE_LOGIN = "/api/app/auth/login";

    @Autowired
    private AuthJwtProperties authJwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("path: " + request.getRequestURI());
        // 验证用户Token信息
        /*if (!StringUtils.equals(DEFAULT_AUTH_ROUTE_LOGIN, request.getRequestURI())) {
            // TODO
        }*/
        System.out.println(authJwtProperties.getSecretKey());
        System.out.println(authJwtProperties.getTokenLeaseSeconds());

        // 权限验证成功，执行后面的操作
        filterChain.doFilter(request, response);
    }
}
