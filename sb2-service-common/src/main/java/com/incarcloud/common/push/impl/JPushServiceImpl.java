package com.incarcloud.common.push.impl;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.incarcloud.common.config.settings.JPushProperties;
import com.incarcloud.common.push.JPushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 极光推送服务实现
 *
 * @author Aaric, created on 2019-09-04T11:37.
 * @since 0.12.0-SNAPSHOT
 */
@Slf4j
@Service
public class JPushServiceImpl implements JPushService {

    /**
     * 默认iOS提示音
     */
    private static final String DEFAULT_IOS_SOUND_NAME = "happy";

    @Autowired
    private JPushProperties jPushProperties;

    @Override
    public Long pushToAndroid(String title, String content, Map<String, String> extraMsg, String... clientId) {
        // 构建消息主体
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.android()) //指定Android平台
                .setAudience(Audience.alias(clientId)) //指定推送设备ID
                .setNotification(Notification.android(title, content, extraMsg))
                .build();

        // 推送消息给设备
        return pushPayload(pushPayload);
    }

    @Override
    public Long pushToApple(String title, String content, Map<String, String> extraMsg, String... clientId) {
        // 构建消息主体
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.ios()) //指定iOS平台
                .setAudience(Audience.alias(clientId)) //指定推送设备ID
                .setNotification(Notification.newBuilder() //构建通知主体
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(title) //消息标题
                                .setContentAvailable(true) //是Background运行的必须参数
                                .incrBadge(1) //角标
                                .setSound(DEFAULT_IOS_SOUND_NAME) //iOS提示音
                                .addExtras(extraMsg) //透传消息
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(jPushProperties.getApnsProduction()) //指定iOS环境APN证书模式: true-为生产模式, false-为测试模式
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtras(extraMsg)
                        .build())
                .build();

        // 推送消息给设备
        return pushPayload(pushPayload);
    }

    @Override
    public Long pushToAll(String title, String content, Map<String, String> extraMsg) {
        // 构建消息主体
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all()) //指定iOS平台
                .setAudience(Audience.all()) //指定推送全部设备ID
                .setNotification(Notification.newBuilder() //构建通知主体
                        .addPlatformNotification(IosNotification.newBuilder() //发送iOS设备消息主体
                                .setAlert(title) //消息标题
                                .incrBadge(1) //角标
                                .setContentAvailable(true) //是Background运行的必须参数
                                .setSound(DEFAULT_IOS_SOUND_NAME) //iOS提示音
                                .addExtras(extraMsg) //透传消息
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder() //发送Android设备消息主体
                                .setAlert(title) //消息体
                                .addExtras(extraMsg) //附加参数
                                .setTitle(content)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(jPushProperties.getApnsProduction()) //指定iOS环境APN证书模式: true-为生产模式, false-为测试模式
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtras(extraMsg)
                        .build())
                .build();

        // 推送消息给设备
        return pushPayload(pushPayload);
    }

    /**
     * 获得JPushClient对象
     *
     * @return
     */
    private JPushClient getJPushClient() {
        return new JPushClient(jPushProperties.getMasterSecret(), jPushProperties.getAppKey());
    }

    /**
     * 推送消息给设备
     *
     * @param pushPayload 消息主体
     * @return
     */
    private Long pushPayload(PushPayload pushPayload) {
        try {
            PushResult result = getJPushClient().sendPush(pushPayload);
            if (null != result && null == result.error) {
                // 返回结果ID
                return result.msg_id;
            } else {
                // 打印错误信息
                log.error("JPush Failure: msgId = {}, errorCode = {}, errorMessage = {}", result.msg_id, result.error.getCode(), result.error.getMessage());
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        return null;
    }
}
