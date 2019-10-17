package com.incarcloud.common.map;

import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsPosition;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpgSeviceTest
 *
 * @author Aaric, created on 2019-10-14T15:29.
 * @since 1.1.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpgSeviceTest {

    @Autowired(required = false)
    protected SpgService spgService;

    @Test
    @Ignore
    public void testGetReGeoInfo() throws Exception {
        /**
         * mcc:460 mnc:0 lac:10173 ci:4430 rxlev:132
         * Nbr: mcc:460 mnc:0 lac:10173 ci:4331 rxlev:131
         * Nbr: mcc:460 mnc:0 lac:10129 ci:3991 rxlev:128
         * Nbr: mcc:460 mnc:0 lac:10173 ci:4000 rxlev:127
         * Nbr: mcc:460 mnc:0 lac:10173 ci:4390 rxlev:126
         * Nbr: mcc:460 mnc:0 lac:10173 ci:4332 rxlev:125
         * Nbr: mcc:460 mnc:0 lac:10173 ci:4442 rxlev:124
         */
        BsPosition position = spgService.getReGeoInfo(
                new BsData(460, 0, 10173, 4430),
                new BsData(460, 0, 10173, 4331),
                new BsData(460, 0, 10129, 3991),
                new BsData(460, 0, 10173, 4000),
                new BsData(460, 0, 10173, 4390),
                new BsData(460, 0, 10173, 4332),
                new BsData(460, 0, 10173, 4442)
        );
        log.info("position: {}", position.toString());
        Assert.assertNotNull(position);
    }
}
