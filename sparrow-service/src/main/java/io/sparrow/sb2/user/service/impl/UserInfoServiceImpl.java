package io.sparrow.sb2.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sparrow.sb2.entity.UserInfo;
import io.sparrow.sb2.user.mapper.UserInfoMapper;
import io.sparrow.sb2.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息服务实现
 *
 * @author Aaric, created on 2019-12-04T15:32.
 * @version 1.4.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public UserInfo getUserInfo(String username) {
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getUsername, username);
        return getBaseMapper().selectOne(wrapper);
    }

    @Override
    public List<String> queryAuthorityList(Integer userId) {
        return getBaseMapper().queryAuthorityList(userId);
    }
}
