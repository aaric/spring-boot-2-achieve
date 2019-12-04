package io.sparrow.sb2.user.service;

import io.sparrow.sb2.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * UserInfoServiceTest
 *
 * @author Aaric, created on 2019-12-04T15:34.
 * @version 1.4.1-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    @Disabled
    public void testGetUserInfoByUserName() {
        UserInfo userInfo = userInfoService.getUserInfo("root");
        log.debug(userInfo.toString());
        Assertions.assertNotNull(userInfo);
    }
}
