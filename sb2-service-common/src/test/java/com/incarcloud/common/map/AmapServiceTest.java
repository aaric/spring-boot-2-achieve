package com.incarcloud.common.map;

import com.alibaba.fastjson.JSON;
import com.incarcloud.common.share.map.GeoPoint;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AmapServiceTest
 *
 * @author Aaric, created on 2019-09-04T17:11.
 * @version 0.13.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AmapServiceTest {

    @Autowired(required = false)
    protected AmapService amapService;

    @Test
    @Disabled
    public void testGetGeoAddress() throws Exception {
        String address = amapService.getReGeoAddress(new GeoPoint(114.403588, 30.475432));
        log.info("address: {}", address);
        Assertions.assertNotNull(address);
    }

    @Test
    @Disabled
    public void testBatchQueryReGeoAddress() throws Exception {
        List<GeoPoint> points = new ArrayList<>();
        points.add(new GeoPoint(114.403588, 30.475432));
        points.add(new GeoPoint(116.310003, 39.991957));
        points.add(new GeoPoint(0.0, 0.0));
        points.add(new GeoPoint(0.0, 0.0));
        points.add(new GeoPoint(114.406728, 30.477141));

        List<String> addressList = amapService.batchQueryReGeoAddress(points);
        addressList.forEach(address -> log.info("address: {}", address));

        Assertions.assertNotNull(addressList);
    }

    @Test
    @Disabled
    public void testGetIpGeoAddress() throws Exception {
        Map<String, String> addressInfo = amapService.getIpGeoAddress("59.173.243.67");
        log.info("addressInfo: {}", JSON.toJSONString(addressInfo));
    }

    @Test
    @Disabled
    public void testGetWeatherInfo() throws Exception {
        Map<String, String> weatherInfo = amapService.getWeatherInfo("420100");
        log.info("weatherInfo: {}", JSON.toJSONString(weatherInfo));
    }
}
