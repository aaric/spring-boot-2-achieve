package com.incarcloud.common.push;

import java.util.Map;

/**
 * 极光推送服务接口
 *
 * @author Aaric, created on 2019-09-04T11:25.
 * @since 0.12.0-SNAPSHOT
 */
public interface JPushService {

    /**
     * 推送消息给Android设备
     *
     * @param title    消息标题
     * @param content  消息内容
     * @param extraMsg 透传消息
     * @param clientId 设备ID
     */
    Long pushToAndroid(String title, String content, Map<String, String> extraMsg, String... clientId);

    /**
     * 推送消息给iOS设备
     *
     * @param title    消息标题
     * @param content  消息内容
     * @param extraMsg 透传消息
     * @param clientId 设备ID
     */
    Long pushToApple(String title, String content, Map<String, String> extraMsg, String... clientId);

    /**
     * 推送消息给所有设备
     *
     * @param title    消息标题
     * @param content  消息内容
     * @param extraMsg 透传消息
     */
    Long pushToAll(String title, String content, Map<String, String> extraMsg);
}
