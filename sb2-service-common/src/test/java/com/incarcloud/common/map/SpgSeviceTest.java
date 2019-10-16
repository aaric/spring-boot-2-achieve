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
        BsPosition position = spgService.getReGeoInfo(new BsData(460, 0, 34860, 62041));
        log.info("position: {}", position.toString());
        Assert.assertNotNull(position);
    }
}
