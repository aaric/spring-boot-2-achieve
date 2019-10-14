package com.incarcloud.common.map.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.incarcloud.common.config.settings.SpgProperties;
import com.incarcloud.common.map.SpgSevice;
import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsResult;
import com.incarcloud.common.utils.OkHttp3Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 基站位置服务实现(gpsspg.com)
 *
 * @author Aaric, created on 2019-10-14T14:16.
 * @since 1.1.0-SNAPSHOT
 */
@Slf4j
public class SpgSeviceImpl implements SpgSevice {

    /**
     * 基站位置服务配置类(gpsspg.com)
     */
    private SpgProperties spgProperties;

    public SpgSeviceImpl(SpgProperties spgProperties) {
        this.spgProperties = spgProperties;
    }

    /**
     * 获得请求REST API地址字符串
     *
     * @param formatData 格式化的基站数据
     * @return
     */
    private String getRestApiUrl(String formatData) {
        return MessageFormat.format("http://{0}/bs/?oid={1}&key={2}&output=json&bs={3}",
                spgProperties.getHostname(),
                spgProperties.getOid(),
                spgProperties.getKey(),
                formatData);
    }

    /**
     * 基站数据格式化
     *
     * @param data 基站数据
     * @return
     */
    private String formatBsDataString(BsData... data) {
        if (null == data) {
            throw new IllegalArgumentException("data is null");
        }

        // 数据格式转换，例如：str0|str1|str2
        List<String> list = new ArrayList<>();
        for (BsData d : data) {
            list.add(d.format());
        }
        return StringUtils.join(list, ",");
    }

    @Override
    public BsResult getReGeoInfo(BsData... data) throws Exception {
        // 构建请求参数
        BsResult bsResult = null;
        String json = OkHttp3Util.get(getRestApiUrl(formatBsDataString(data)), null);

        // 解析json字符串
        if (StringUtils.isNotBlank(json)) {
            JSONObject root = JSONObject.parseObject(json);
            if ("200".equals(root.getString("status")) && "ok".equals(root.getString("msg"))) {
                // 判断依赖数据节点信息
                JSONArray result = root.getJSONArray("result");
                if (null == result || 0 == result.size()) {
                    return null;
                }
                JSONObject result0 = result.getJSONObject(0);
                if (null == result0) {
                    return null;
                }

                // 构建返回数据
                bsResult = new BsResult();
                bsResult.setId(result0.getString("id"));
                bsResult.setLongitude(Double.valueOf(result0.getString("lng")));
                bsResult.setLatitude(Double.valueOf(result0.getString("lat")));
                bsResult.setRadius(Integer.valueOf(result0.getString("radius")));
                bsResult.setAddress(result0.getString("address"));
                bsResult.setRoads("roads");
                bsResult.setRid(result0.getString("rid"));
                bsResult.setRids(result0.getString("rids"));
            }
        }

        return bsResult;
    }
}
