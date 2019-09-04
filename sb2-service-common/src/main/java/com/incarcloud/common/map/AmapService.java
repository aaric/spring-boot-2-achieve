package com.incarcloud.common.map;

import com.incarcloud.common.share.map.GeoPoint;

/**
 * 高德地图服务接口
 *
 * @author Aaric, created on 2019-09-04T17:09.
 * @since 0.13.0-SNAPSHOT
 */
public interface AmapService {

    /**
     * 查询逆地理编码地址信息
     *
     * @param point 地理位置
     * @return
     */
    String getGeoAddress(GeoPoint point);
}
