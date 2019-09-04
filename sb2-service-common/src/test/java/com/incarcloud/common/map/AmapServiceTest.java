package com.incarcloud.common.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AmapServiceTest
 *
 * @author Aaric, created on 2019-09-04T17:11.
 * @since 0.13.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AmapServiceTest {

    @Autowired
    protected AmapService aMapService;

    @Test
    public void testGetGeoAddress() {
        aMapService.getGeoAddress(null);
    }
}
