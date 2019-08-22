package com.incarcloud.common.msg;

/**
 * 发送邮件服务接口
 *
 * @author Aaric, created on 2019-08-22T11:46.
 * @since 0.8.1-SNAPSHOT
 */
public interface EmailService {

    /**
     * 发送HTML模板邮件
     *
     * @param subject     邮件主题
     * @param htmlContent HTML内容
     * @param tos         收件人
     * @return
     */
    boolean sendTemplate(String subject, String htmlContent, String... tos);
}
