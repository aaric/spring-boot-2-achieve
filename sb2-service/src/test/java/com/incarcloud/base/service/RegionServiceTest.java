package com.incarcloud.base.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.incarcloud.pojo.dto.RegionDto;
import com.incarcloud.pojo.entity.Region;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;
import java.util.List;

/**
 * RegionServiceTest
 *
 * @author Aaric, created on 2019-11-12T16:30.
 * @version 1.3.1-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    /**
     * 获得省市区全路径字符串
     *
     * @param strings 代码或者名称
     * @return
     */
    private static String getPacPath(String... strings) {
        return StringUtils.join(strings, "/");
    }

    @Test
    @Disabled
    public void testInitDB() throws Exception {
        // 初始化中国省市区三级联动数据库
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("pca-code.json")) {
            String data = IOUtils.toString(input);
            // 第一级：省
            JSONArray provinces = JSON.parseArray(data);
            for (int i = 0; i < provinces.size(); i++) {
                JSONObject province = provinces.getJSONObject(i);
                String provinceCode = province.getString("code");
                String provinceName = province.getString("name");
                log.debug("code: {}, name: {}", provinceCode, provinceName);
                Integer provinceId = regionService.savePca(null, 1, provinceCode, provinceName,
                        provinceCode, provinceName);

                // 第二级：市
                JSONArray cities = province.getJSONArray("children");
                for (int j = 0; j < cities.size(); j++) {
                    JSONObject city = cities.getJSONObject(j);
                    String cityCode = city.getString("code");
                    String cityName = city.getString("name");
                    log.debug("\tcode: {}, name: {}", cityCode, cityName);
                    Integer cityId = regionService.savePca(provinceId, 2, cityCode, cityName,
                            getPacPath(provinceCode, cityCode), getPacPath(provinceName, cityName));

                    // 第三级：区
                    JSONArray areas = city.getJSONArray("children");
                    for (int k = 0; k < areas.size(); k++) {
                        JSONObject area = areas.getJSONObject(k);
                        String areaCode = area.getString("code");
                        String areaName = area.getString("name");
                        log.debug("\t\tcode: {}, name: {}", areaCode, areaName);
                        regionService.savePca(cityId, 3, areaCode, areaName,
                                getPacPath(provinceCode, cityCode, areaCode),
                                getPacPath(provinceName, cityName, areaName));
                    }
                }
            }
        }

        // ok
        log.debug("ok!");
    }

    @Test
    @Ignore
    public void testQueryPacTreeList() throws Exception {
        List<RegionDto> list = regionService.queryPacTreeList();
        Assertions.assertEquals(31, list.size());
    }

    @Test
    @Ignore
    public void testGet() throws Exception {
        Region region = regionService.get(1);
        Assertions.assertNotNull(region);
    }
}
