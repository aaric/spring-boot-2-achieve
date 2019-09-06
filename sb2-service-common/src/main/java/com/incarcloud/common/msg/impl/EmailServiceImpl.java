package com.incarcloud.common.msg.impl;

import com.incarcloud.common.config.settings.EmailProperties;
import com.incarcloud.common.msg.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;

/**
 * 发送邮件服务实现
 *
 * @author Aaric, created on 2019-08-22T11:47.
 * @since 0.8.1-SNAPSHOT
 */
@Slf4j
public class EmailServiceImpl implements EmailService {

    /**
     * 发送邮件服务配置
     */
    private EmailProperties emailProperties;

    public EmailServiceImpl(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Override
    public boolean sendTemplate(String subject, String htmlContent, String... tos) {
        try {
            // 定义MimeMessage对象
            MimeMessage msg = new MimeMessage(getSession());

            // 设置收件人/发件人信息
            msg.setFrom(new InternetAddress(emailProperties.getAccount()));
            msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(StringUtils.join(tos, ","), false));

            /*** ============ 主题和内容 ============ ***/
            // 主题
            msg.setSubject(subject);
            msg.setSentDate(Calendar.getInstance().getTime());

            // 构建邮件内容
            Multipart mp = new MimeMultipart("related");

            // 构建HTML文本
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(htmlContent, EmailProperties.DEFAULT_CONTENT_TYPE);
            mp.addBodyPart(htmlBodyPart);

            // 特殊处理某些不支持正常的发送邮件地址后缀
            if (tos[0].endsWith("@qq.com")) {
                // 设置HTML内容
                msg.setContent(mp);
            } else {
                // 设置纯文本内容
                // "@163.com | @126.com | @yeah.net
                msg.setContent(htmlContent, EmailProperties.DEFAULT_CONTENT_TYPE);
            }
            /*** ============ 主题和内容 ============ ***/

            // 发送邮件
            Transport.send(msg);
            return true;

        } catch (Exception e) {
            // 打印日志
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    /**
     * 配置邮件属性和Session对象
     *
     * @return
     */
    private Session getSession() {
        // 加载配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", emailProperties.getHost());
        props.setProperty("mail.stmp.port", String.valueOf(emailProperties.getPort()));
        props.setProperty("mail.smtp.auth", "true");

        // 返回Session对象
        return Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 设置发件人用户名和密码
                return new PasswordAuthentication(emailProperties.getAccount(), emailProperties.getPassword());
            }

        });
    }
}
