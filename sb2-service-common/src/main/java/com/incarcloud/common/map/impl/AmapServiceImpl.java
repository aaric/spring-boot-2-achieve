package com.incarcloud.common.map.impl;

import com.incarcloud.common.config.settings.AmapProperties;
import com.incarcloud.common.map.AmapService;
import com.incarcloud.common.share.map.GeoPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 高德地图服务实现
 *
 * @author Aaric, created on 2019-09-04T17:10.
 * @since 0.13.0-SNAPSHOT
 */
@Slf4j
@Service
public class AmapServiceImpl implements AmapService {

    @Autowired
    private AmapProperties amapProperties;

    @Override
    public String getGeoAddress(GeoPoint point) {
        log.info("AmapProperties: " + amapProperties.toString());
        return null;
    }
}
