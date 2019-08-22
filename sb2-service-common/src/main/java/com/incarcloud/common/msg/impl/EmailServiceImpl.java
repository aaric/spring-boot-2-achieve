package com.incarcloud.common.msg.impl;

import com.incarcloud.common.config.settings.EmailProperties;
import com.incarcloud.common.msg.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public boolean sendTemplate(String subject, String htmlContent, String... tos) {
        // 加载配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", emailProperties.getHost());
        props.setProperty("mail.stmp.port", String.valueOf(emailProperties.getPort()));
        props.setProperty("mail.smtp.auth", "true");

        try {
            // 定义MimeMessage对象
            MimeMessage msg = new MimeMessage(Session.getInstance(props, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 设置发件人用户名和密码
                    return new PasswordAuthentication(emailProperties.getAccount(), emailProperties.getPassword());
                }

            }));

            // 设置收件人/发件人信息
            msg.setFrom(new InternetAddress(emailProperties.getAccount()));
            msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(StringUtils.join(tos, ","), false));

            /*** ============ 主题和内容 ============ ***/
            // 主题
            msg.setSubject(subject);
            msg.setSentDate(Calendar.getInstance().getTime());

            // 构建邮件内容
            Multipart mp = new MimeMultipart("related");

            // LOGO
            /*File logoFile = new File(Thread.currentThread().getContextClassLoader().getResource("logo.png").getFile());
            if (logoFile.exists()) {
                BodyPart logoBodyPart = new MimeBodyPart();
                logoBodyPart.setDataHandler(new DataHandler(new FileDataSource(logoFile)));
                logoBodyPart.setFileName(MimeUtility.encodeText("logo.png"));
                logoBodyPart.setHeader("Content-ID", "<LOGO>");
                mp.addBodyPart(logoBodyPart);
            }*/

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
                // tos[0].endsWith("@163.com") || tos[0].endsWith("@126.com") || tos[0].endsWith("@yeah.net")
                /*String logoImageSrc = "http://sample.com/logo.png";
                htmlContent = htmlContent.replace("cid:LOGO", logoImageSrc);*/
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
}
