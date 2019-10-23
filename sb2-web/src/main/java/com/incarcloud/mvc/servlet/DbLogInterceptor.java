package com.incarcloud.mvc.servlet;

import com.incarcloud.common.share.log.DbLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * DbLog拦截器
 *
 * @author Aaric, created on 2019-10-23T14:17.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
public class DbLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("DbLogInterceptor: {}", request.getRequestURI());
        // 如果不支持HandlerMethod，不拦截该请求
        if ((!(handler instanceof HandlerMethod))) {
            return true;
        }

        // 获取DbLog注解日志配置
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        DbLog dbLog = method.getAnnotation(DbLog.class);
        if (null != dbLog) {
            log.info("tag: {}, title: {}, content: {}", dbLog.tag(), dbLog.title(), dbLog.content());
        }

        return true;
    }
}
