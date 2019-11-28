package com.incarcloud.mvc.servlet;

import com.incarcloud.base.service.LogService;
import com.incarcloud.common.share.log.DbLog;
import com.incarcloud.common.share.log.DbLogSubmit;
import com.incarcloud.pojo.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.Timestamp;
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
    private String bizTag;

    /**
     * 日志提交人
     */
    private DbLogSubmit dbLogSubmit;

    /**
     * 日志服务
     */
    private LogService logService;

    public DbLogInterceptor(String bizTag, LogService logService, DbLogSubmit dbLogSubmit) {
        this.bizTag = bizTag;
        this.logService = logService;
        this.dbLogSubmit = dbLogSubmit;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录访问信息
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(DbLog.DEFAULT_VISIT_START_KEY, Instant.now().toEpochMilli());
        return true;
    }

    @SuppressWarnings("checkstyle:HiddenField")
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
            // 获取日志内容
            String logContent = dbLog.content();
            HttpSession httpSession = request.getSession();
            Object[] objects = (Object[]) httpSession.getAttribute(dbLog.contentObjectsKey()); // DbLog.DEFAULT_CONTENT_OBJECTS_KEY
            if (null != objects) {
                logContent = new MessageFormat(logContent).format(objects);
            }

            // 获取服务端处理时间戳
            long serverProcessStart = (long) httpSession.getAttribute(DbLog.DEFAULT_VISIT_START_KEY);
            Timestamp serverProcessStartTime = Timestamp.from(Instant.ofEpochMilli(serverProcessStart));

            // 获得客户端请求时间戳
            long clientConnectInterval = -1L;
            Timestamp clientConnectStartTime = null;
            String clientConnectTimeString = request.getParameter(DbLog.DEFAULT_CLIENT_TIME_KEY);
            if (StringUtils.isNotBlank(clientConnectTimeString)) {
                long clientConnectStart = Long.valueOf(clientConnectTimeString);
                clientConnectStartTime = Timestamp.from(Instant.ofEpochMilli(clientConnectStart));
                clientConnectInterval = serverProcessStart - clientConnectStart;
            }

            // 获取异常信息
            String exceptionDetail = (String) httpSession.getAttribute(DbLog.DEFAULT_EXCEPTION_DETAIL_KEY);

            // 打印日志信息
            if (null != dbLog) {
                // 构建日志信息
                Log log = new Log();
                log.setTagName(bizTag) //基本信息
                        .setGroupName(dbLog.group())
                        .setTitle(dbLog.title())
                        .setContent(logContent)
                        .setRemark(dbLog.remark())
                        .setSubmit(dbLogSubmit.getSubmit());
                log.setHttpType(request.getMethod()) //HTTP请求信息
                        .setHttpUrl(request.getRequestURI())
                        .setHttpStatus(dbLog.httpStatus());
                Instant serverProcessEnd = Instant.now();
                log.setClientConnectStart(clientConnectStartTime) //记录时间点
                        .setServerProcessStart(serverProcessStartTime)
                        .setServerProcessEnd(Timestamp.from(serverProcessEnd));
                log.setClientConnectInterval(Math.toIntExact(clientConnectInterval)) //耗时信息
                        .setServerProcessInterval(Math.toIntExact(serverProcessEnd.toEpochMilli() - serverProcessStart));
                log.setExceptionDetail(exceptionDetail); //异常信息

                // 存储到数据库
                logService.saveLog(log);
            }
        }

    }
}
