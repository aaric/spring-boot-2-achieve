package com.incarcloud.base.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @Test
    @Disabled
    public void testInitDbData() {
        regionService.initDbData();
    }
}
