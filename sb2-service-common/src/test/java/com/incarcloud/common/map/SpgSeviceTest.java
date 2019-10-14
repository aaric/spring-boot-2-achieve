package com.incarcloud.common.map;

import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsResult;
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
    protected SpgSevice spgSevice;

    @Test
    @Ignore
    public void testGetReGeoInfo() throws Exception {
        BsResult result = spgSevice.getReGeoInfo(new BsData(460, 0, 34860, 62041));
        log.info("result: {}", result.toString());
        Assert.assertNotNull(result);
    }
}
