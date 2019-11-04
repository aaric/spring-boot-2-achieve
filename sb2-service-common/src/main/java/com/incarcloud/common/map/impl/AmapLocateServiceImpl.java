package com.incarcloud.common.map.impl;

import com.alibaba.fastjson.JSONObject;
import com.incarcloud.common.config.settings.AmapLocateProperties;
import com.incarcloud.common.map.AmapLocateService;
import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsPosition;
import com.incarcloud.common.utils.OkHttp3Util;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 高德地图服务（基站定位）实现
 *
 * @author Aaric, created on 2019-11-04T11:16.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
public class AmapLocateServiceImpl implements AmapLocateService {

    /**
     * 高德地图服务配置（基站定位）
     */
    private AmapLocateProperties amapLocateProperties;

    public AmapLocateServiceImpl(AmapLocateProperties amapLocateProperties) {
        this.amapLocateProperties = amapLocateProperties;
    }

    /**
     * 获得请求Locate API地址字符串
     *
     * @return
     */
    private String getLocateApiUrl() {
        return MessageFormat.format("http://{0}/position", amapLocateProperties.getHostname());
    }

    @Override
    public BsPosition getAddress(@NonNull String imei, @NonNull BsData bts, List<BsData> nearBts) throws Exception {
        // 构建请求参数
        BsPosition position = null;
        Map<String, String> params = new HashMap<>();
        params.put("accesstype", "0"); //移动端接入网络方式
        params.put("imei", imei); //SIM卡IMEI标识
        params.put("cdma", "0"); //非CDMA模式
        params.put("bts", bts.format()); //接入基站信息
        if (null != nearBts && 0 != nearBts.size()) {
            params.put("nearbts", nearBts.stream().map(object -> object.format()).collect(Collectors.joining("|"))); //周边基站信息
        }
        params.put("output", "json"); //返回JSON格式数据
        params.put("key", amapLocateProperties.getKey());

        // 请求数据
        String json = OkHttp3Util.get(getLocateApiUrl(), params);

        // 解析json字符串
        if (StringUtils.isNotBlank(json)) {
            //log.debug("json: {}", json);
            JSONObject root = JSONObject.parseObject(json);
            if (StringUtils.equals("OK", root.getString("info")) && StringUtils.equals("1", root.getString("status"))) {
                JSONObject result = root.getJSONObject("result");
                // type定位类型：0-没有得到定位结果，其他数字为-正常获取定位结果
                if (!StringUtils.equals("0", result.getString("type"))) {
                    // 构建返回数据
                    position = new BsPosition();
                    position.setRadius(Integer.valueOf(result.getString("radius")));
                    position.setAddress(result.getString("desc"));
                    position.setRoad(result.getString("road"));
                    position.setCode6(result.getString("adcode"));
                    position.setLongitude(0D);
                    position.setLatitude(0D);

                    // 设置经纬度信息，手机平台接口读出的数值
                    String location = result.getString("location");
                    if (StringUtils.isNotBlank(location) && StringUtils.contains(location, ",")) {
                        String[] lnglat = location.split(",");
                        if (null != lnglat && 2 == lnglat.length) {
                            position.setLongitude(Double.valueOf(lnglat[0]));
                            position.setLatitude(Double.valueOf(lnglat[1]));
                        }
                    }
                }
            }
        }

        return position;
    }
}
