package com.incarcloud.common.map;

import com.incarcloud.common.share.map.GeoPoint;

import java.util.List;
import java.util.Map;

/**
 * 高德地图服务接口
 *
 * @author Aaric, created on 2019-09-04T17:09.
 * @version 0.13.0-SNAPSHOT
 */
public interface AmapService {

    /**
     * 查询逆地理编码地址信息
     *
     * @param point 地理位置点
     * @return
     * @throws Exception
     */
    String getReGeoAddress(GeoPoint point) throws Exception;

    /**
     * 批量查询逆地理编码地址信息
     *
     * @param points 地理位置点
     * @return
     * @throws Exception
     */
    List<String> batchQueryReGeoAddress(List<GeoPoint> points) throws Exception;

    /**
     * 查询客户端IP地理地址信息
     *
     * @param clientIp 客户端IP地址
     * @return
     * @throws Exception
     */
    Map<String, String> getIpGeoAddress(String clientIp) throws Exception;

    /**
     * 查询天气信息（非付费用户只能获得实时天气信息）
     *
     * @param adcode 城市编码
     * @return
     * @throws Exception
     */
    Map<String, String> getWeatherInfo(String adcode) throws Exception;
}
