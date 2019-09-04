package com.incarcloud.common.sms.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.incarcloud.common.config.settings.DysmsProperties;
import com.incarcloud.common.sms.DysmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 阿里云发短信服务实现
 *
 * @author Aaric, created on 2019-08-23T14:28.
 * @since 0.10.0-SNAPSHOT
 */
@Slf4j
@Service
public class DysmsServiceImpl implements DysmsService {

    @Autowired
    private DysmsProperties dysmsProperties;

    @Override
    @SuppressWarnings("deprecation")
    public String sendTemplate(String templateCode, Map<String, String> templateParams, String outId, String... tos) {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(dysmsProperties.getConnectTimeout()));
        System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(dysmsProperties.getReadTimeout()));

        try {
            // 初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile(dysmsProperties.getRegionId(),
                    dysmsProperties.getAccessKeyId(), dysmsProperties.getAccessKeySecret());
            DefaultProfile.addEndpoint(dysmsProperties.getEndpoint(), dysmsProperties.getRegionId(),
                    DysmsProperties.DEFAULT_PRODUCT, DysmsProperties.DEFAULT_DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            // 必填:待发送手机号
            request.setPhoneNumbers(StringUtils.join(tos, ","));
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(dysmsProperties.getSignName());
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(JSON.toJSONString(templateParams));
            // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(outId);

            // hint 此处可能会抛出异常，注意catch
            SendSmsResponse response = acsClient.getAcsResponse(request);
            // BizId: 616622542071657945^0, Code: OK, Message: OK
            if (null != response && StringUtils.equals("OK", response.getCode())) {
                return response.getBizId();
            }

        } catch (ClientException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}
