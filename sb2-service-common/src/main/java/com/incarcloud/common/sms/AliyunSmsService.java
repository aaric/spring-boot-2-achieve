package com.incarcloud.common.sms;

import java.util.Map;

/**
 * 阿里云短信服务接口
 *
 * @author Aaric, created on 2019-08-23T14:27.
 * @since 0.10.0-SNAPSHOT
 */
public interface AliyunSmsService {

    /**
     * 发送短信
     *
     * @param templateCode   短信模板
     * @param templateParams 模板中的变量参数
     * @param outId          外部流水扩展字段
     * @param tos            发送人
     * @return
     */
    String sendTemplate(String templateCode, Map<String, String> templateParams, String outId, String... tos);
}
