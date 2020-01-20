package io.sparrow.sb2.user.service;

import io.sparrow.sb2.entity.UserInfo;

import java.util.List;

/**
 * 用户信息服务接口
 *
 * @author Aaric, created on 2019-12-04T15:28.
 * @version 1.4.1-SNAPSHOT
 */
public interface UserInfoService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     */
    UserInfo getUserInfo(String username);

    /**
     * 查询授权信息
     *
     * @param userId 用户ID
     * @return
     */
    List<String> queryAuthorityList(Integer userId);
}
