package com.incarcloud.common.map.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.incarcloud.common.config.settings.AmapProperties;
import com.incarcloud.common.map.AmapService;
import com.incarcloud.common.share.map.GeoPoint;
import com.incarcloud.common.utils.OkHttp3Util;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 高德地图服务实现
 *
 * @author Aaric, created on 2019-09-04T17:10.
 * @since 0.13.0-SNAPSHOT
 */
@Slf4j
@Service
public class AmapServiceImpl implements AmapService {

    /**
     * 默认无效位置返回"未知"
     */
    private static final String DEFAULT_INVALID_ADDRESS = "未知";

    @Autowired
    private AmapProperties amapProperties;

    @Override
    public String getReGeoAddress(@NonNull GeoPoint point) throws Exception {
        // 无效位置(0, 0)，直接返回“未知”
        if (0 == point.getLongitude() && 0 == point.getLatitude()) {
            return DEFAULT_INVALID_ADDRESS;
        }

        // 获得坐标转换后的位置字符串
        @NonNull String convertPointsString = batchQueryConvertPoints(Arrays.asList(point));

        // 构建高德API逆地理编码请求参数信息
        Map<String, String> params = getRestApiKeyParameters();
        params.put("location", convertPointsString);
        params.put("extensions", "base");

        // 请求网络查询数据
        String result = OkHttp3Util.get(getRestApiUrl("geocode/regeo"), params);

        // 解析json字符串
        if (StringUtils.isNotBlank(result)) {
            JSONObject root = JSONObject.parseObject(result);
            if ("1".equals(root.getString("status"))) {
                JSONObject regeocode = root.getJSONObject("regeocode");
                String address = regeocode.getString("formatted_address");
                return "[]".equals(address) ? DEFAULT_INVALID_ADDRESS : address;
            }
        }
        return null;
    }

    /**
     * 解析批量地址数据
     *
     * @param root              JSON对象
     * @param originPoints      原始地理位置
     * @param legalPointStrings 合法地理位置字符串
     * @return
     */
    private List<String> batchAddressList(JSONObject root, List<GeoPoint> originPoints, List<String> legalPointStrings) {
        String address;
        JSONArray regeocodes = root.getJSONArray("regeocodes");
        if (null != regeocodes && 0 != regeocodes.size()) {
            // 构建合法逆编码地址MAP集合
            Map<String, String> legalAddressMap = new HashMap<>();
            for (int i = 0; i < legalPointStrings.size(); i++) {
                address = regeocodes.getJSONObject(i).getString("formatted_address"); //error deal
                legalAddressMap.put(legalPointStrings.get(i), address);
            }

            // 构建返回正确的地址信息集合
            List<String> returnAddressList = new ArrayList<>();
            for (GeoPoint point : originPoints) {
                address = legalAddressMap.get(point.getLongitude() + "," + point.getLatitude());
                if (StringUtils.isNotBlank(address)) {
                    returnAddressList.add(address);
                } else {
                    returnAddressList.add(DEFAULT_INVALID_ADDRESS);
                }
            }
            return returnAddressList;
        }
        return null;
    }

    @Override
    public List<String> batchQueryReGeoAddress(@NonNull List<GeoPoint> points) throws Exception {
        // 构建location参数集合
        List<GeoPoint> legalPoints = points.stream()
                .filter(object -> 0.0 != object.getLongitude() && 0.0 != object.getLatitude())
                .collect(Collectors.toList());

        // 有效点的数量必须大于0
        if (null != legalPoints && 0 != legalPoints.size()) {
            // 构建location字符串集合
            List<String> legalPointStrings = legalPoints.stream()
                    .map(object -> object.getLongitude() + "," + object.getLatitude())
                    .collect(Collectors.toList());

            // 获得合法坐标转换后的位置字符串
            String legalPointsString = batchQueryConvertPoints(legalPoints);

            // 构建高德API逆地理编码请求参数信息
            Map<String, String> params = getRestApiKeyParameters();
            params.put("location", legalPointsString);
            params.put("batch", "true");
            params.put("extensions", "base");

            // 请求网络查询数据
            String result = OkHttp3Util.get(getRestApiUrl("geocode/regeo"), params);

            // 解析json字符串
            if (StringUtils.isNotBlank(result)) {
                JSONObject root = JSONObject.parseObject(result);
                if (!"1".equals(root.getString("status"))) {
                    // 打印错误日志
                    String locationsString = legalPointStrings.stream().collect(Collectors.joining("|"));
                    log.error("batchQueryReGeoAddress failure, input: {}, errorInfo: {}", locationsString, root.getString("info"));
                    return null;
                }

                // 返回地址信息
                return batchAddressList(root, points, legalPointStrings);
            }
        }

        // 其他情况：返回全部查询位置“未知”
        String[] defaultAddresses = new String[points.size()];
        Arrays.fill(defaultAddresses, DEFAULT_INVALID_ADDRESS);

        return Arrays.asList(defaultAddresses);
    }

    @Override
    public Map<String, String> getIpGeoAddress(@NonNull String clientIp) throws Exception {
        // 构建高德API查询IP地理位置请求参数信息
        Map<String, String> params = getRestApiKeyParameters();
        params.put("ip", clientIp);

        // 请求网络查询数据
        String result = OkHttp3Util.get(getRestApiUrl("ip"), params);

        // 解析json字符串
        if (StringUtils.isNotBlank(result)) {
            JSONObject root = JSONObject.parseObject(result);
            if ("1".equals(root.getString("status"))) {
                Map<String, String> addressInfo = new HashMap<>();
                addressInfo.put("province", root.getString("province"));
                addressInfo.put("city", root.getString("city"));
                addressInfo.put("adcode", root.getString("adcode"));
                addressInfo.put("rectangle", root.getString("rectangle"));
                return addressInfo;
            }
        }
        return null;
    }

    @Override
    public Map<String, String> getWeatherInfo(String adcode) throws Exception {
        // 构建高德API查询城市天气请求参数信息
        Map<String, String> params = getRestApiKeyParameters();
        params.put("city", adcode);

        // 请求网络查询数据
        String result = OkHttp3Util.get(getRestApiUrl("weather/weatherInfo"), params);

        // 解析json字符串
        if (StringUtils.isNotBlank(result)) {
            JSONObject root = JSONObject.parseObject(result);
            if ("1".equals(root.getString("status"))) {
                JSONArray lives = root.getJSONArray("lives");
                if (null != lives && 1 == lives.size()) {
                    JSONObject lives0 = lives.getJSONObject(0);
                    Map<String, String> weatherInfo = new HashMap<>();
                    weatherInfo.put("province", lives0.getString("province"));
                    weatherInfo.put("city", lives0.getString("city"));
                    weatherInfo.put("adcode", lives0.getString("adcode"));
                    weatherInfo.put("weather", lives0.getString("weather"));
                    weatherInfo.put("temperature", lives0.getString("temperature"));
                    weatherInfo.put("winddirection", lives0.getString("winddirection"));
                    weatherInfo.put("windpower", lives0.getString("windpower"));
                    weatherInfo.put("humidity", lives0.getString("humidity"));
                    weatherInfo.put("reporttime", lives0.getString("reporttime"));
                    return weatherInfo;
                }
            }
        }
        return null;
    }

    /**
     * 获得请求REST API地址字符串
     *
     * @param urlKey 请求地址Key
     * @return
     */
    private String getRestApiUrl(String urlKey) {
        return MessageFormat.format("https://{0}/v3/{1}", amapProperties.getHostname(), urlKey);
    }

    /**
     * 获得公共请求参数信息
     *
     * @return
     */
    private Map<String, String> getRestApiKeyParameters() {
        Map<String, String> params = new HashMap<>();
        params.put("key", amapProperties.getKey()); //授权Key
        params.put("output", "json"); //返回格式
        return params;
    }

    /**
     * 查询坐标转换位置
     *
     * @param points 地理位置点
     * @return
     */
    private String batchQueryConvertPoints(@NonNull List<GeoPoint> points) throws Exception {
        // 构建locations字符串
        String locationsString = points.stream()
                .map(object -> object.getLongitude() + "," + object.getLatitude())
                .collect(Collectors.joining("|"));

        // 构建高德API逆地理编码请求地址
        Map<String, String> params = getRestApiKeyParameters();
        params.put("locations", locationsString);
        params.put("coordsys", "gps");

        // 请求网络查询数据
        String result = OkHttp3Util.get(getRestApiUrl("assistant/coordinate/convert"), params);

        // 解析json字符串
        if (StringUtils.isNotBlank(result)) {
            JSONObject root = JSONObject.parseObject(result);
            if ("1".equals(root.getString("status"))) {
                // 返回坐标转换后的位置字符串
                return root.getString("locations").replaceAll(";", "\\|");
            } else {
                // 打印错误日志
                log.error("batchQueryConvertPoints failure, input: {}, errorInfo: {}", locationsString, root.getString("info"));
            }
        }
        return null;
    }
}
