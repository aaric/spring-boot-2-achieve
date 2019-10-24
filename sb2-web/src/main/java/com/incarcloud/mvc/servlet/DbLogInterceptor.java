package com.incarcloud.mvc.servlet;

import com.incarcloud.common.share.Constant;
import com.incarcloud.common.share.log.DbLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.Instant;

/**
 * DbLog拦截器
 *
 * @author Aaric, created on 2019-10-23T14:17.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
public class DbLogInterceptor implements HandlerInterceptor {

    /**
     * 业务标签
     */
    @Value("${" + Constant.DEFAULT_ENTERPRISE_CODE + ".biz.tag" + "}")
    private String bizTag;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录访问信息
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(DbLog.DEFAULT_VISIT_START_KEY, Instant.now().toEpochMilli());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不支持反射，不拦截该请求
        if ((!(handler instanceof HandlerMethod))) {
            return;
        }

        // 获取DbLog注解日志配置
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        DbLog dbLog = method.getAnnotation(DbLog.class);
        if (null != dbLog) {
            // 获取请求信息
            HttpSession httpSession = request.getSession();
            long dbLogVisitStart = (long) httpSession.getAttribute(DbLog.DEFAULT_VISIT_START_KEY);

            // 获取日志内容
            String dbLogContent = dbLog.content();
            Object[] objects = (Object[]) httpSession.getAttribute(DbLog.DEFAULT_CONTENT_OBJECTS_KEY);
            if (null != objects) {
                dbLogContent = new MessageFormat(dbLogContent).format(objects);
            }

            // 获取用户信息，一般是UID
            String dbLogSubmit = "";
            String uid = (String) httpSession.getAttribute(DbLog.DEFAULT_CURRENT_UID_KEY);
            if (StringUtils.isNotBlank(uid)) {
                dbLogSubmit = uid;
            }

            // 获取异常信息
            String dbLogExceptionDetail = "no exception";
            if (null != ex) {
                dbLogExceptionDetail = ExceptionUtils.getStackTrace(ex);
            }

            // 打印日志信息
            if (null != dbLog) {
                log.debug("\n## DbLog:\n----- \nhttpType: {}, \nhttpUrl: {}, \nhttpStatus:{},"
                                + "\ntag: {}, \ntitle: {}, \ncontent: {}, \nremark: {}, "
                                + "\nsubmit:{}, \nhttpInterval: {}ms, \nexceptionDetail: {}\n-----",
                        request.getMethod(), request.getRequestURI(), dbLog.httpStatus(),
                        bizTag, dbLog.title(), dbLogContent, StringUtils.appendIfMissing(dbLog.remark(), "no remark"),
                        dbLogSubmit, (Instant.now().toEpochMilli() - dbLogVisitStart), dbLogExceptionDetail
                );
            }
        }

    }
}
