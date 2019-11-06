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

import java.util.ArrayList;
import java.util.List;

/**
 * AmapLocateServiceTest
 *
 * @author Aaric, created on 2019-11-04T12:46.
 * @version 1.2.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AmapLocateServiceTest {

    @Autowired(required = false)
    private AmapLocateService amapLocateService;

    @Test
    @Ignore
    public void testGetAddress() throws Exception {
        BsData bts = new BsData(460, 0, 10173, 4430, 132);
        List<BsData> nearBts = new ArrayList<>();
        nearBts.add(new BsData(460, 0, 10173, 4331, 131));
        nearBts.add(new BsData(460, 0, 10129, 3991, 128));
        nearBts.add(new BsData(460, 0, 10173, 4000, 127));
        nearBts.add(new BsData(460, 0, 10173, 4390, 126));
        nearBts.add(new BsData(460, 0, 10173, 4332, 125));
        nearBts.add(new BsData(460, 0, 10173, 4442, 124));
        BsPosition position = amapLocateService.getAddress("862233026116709", bts, nearBts);
        log.info("position: {}", position.toString());
        Assert.assertNotNull(position);
    }
}
