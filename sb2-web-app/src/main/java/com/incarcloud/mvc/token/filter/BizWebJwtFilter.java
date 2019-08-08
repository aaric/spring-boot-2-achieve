package com.incarcloud.mvc.token.filter;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("path: " + request.getRequestURI());
    }
}
