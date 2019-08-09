package com.incarcloud.mvc.token.filter;

import com.alibaba.fastjson.JSON;
import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.mvc.token.JwtHelper;
import com.incarcloud.mvc.token.settings.AuthJwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    @Autowired
    protected JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 过滤掉静态资源和登录API路由
        if (StringUtils.endsWithAny(request.getRequestURI(), DEFAULT_AUTH_ROUTE_LOGIN,
                "/swagger-resources", "/v2/api-docs", "/doc.html", "/webjars/bycdao-ui/**")) {
            // 不拦截以上请求
            filterChain.doFilter(request, response);
        }

        // 获得客户端ID和Token字符串
        String cid = request.getHeader(AuthJwtProperties.DEFAULT_CID_HEADER_NAME);
        String token = request.getHeader(AuthJwtProperties.DEFAULT_TOKEN_HEADER_NAME);

        System.out.println(cid);
        System.out.println(token);

        // 考虑未携带Token非法请求的情况
        if (StringUtils.isBlank(cid) || StringUtils.isBlank(token)) {
            // 提示：非法请求
            doResponse(response, ResponseData.error(ResponseData.ERROR_0001).extraMsg("非法请求"));
        }

        // 验证客户端Token信息
        try {
            // 使用JWT辅助类验证
            if (jwtHelper.validateToken(cid, token)) {
                // 验证通过
                filterChain.doFilter(request, response);
            } else {
                // 提示：用户未登录
                doResponse(response, ResponseData.error(ResponseData.ERROR_0031).extraMsg("用户未登录"));
            }

        } catch (ApiException e) {
            // 处理自定义异常
            doResponse(response, ResponseData.error(e.getCode()).extraMsg(e.getMessage()));

        } catch (Exception e) {
            // 提示：未知错误
            doResponse(response, ResponseData.error(ResponseData.ERROR_0003).extraMsg("未知错误"));
        }
    }

    /**
     * 处理请求，回馈JSON数据
     *
     * @throws IOException
     */
    private <T> void doResponse(HttpServletResponse response, T data) throws IOException {
        // 设置JSON数据格式
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        // 返回数据
        PrintWriter output = response.getWriter();
        output.write(JSON.toJSONString(data));
        output.flush();
        output.close();
    }
}
