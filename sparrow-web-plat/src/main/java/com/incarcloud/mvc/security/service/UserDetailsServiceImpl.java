package com.incarcloud.mvc.security.service;

import com.incarcloud.mvc.security.entity.LoginSuccessInfo;
import io.sparrow.sb2.entity.UserInfo;
import io.sparrow.sb2.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 用户认证服务实现
 *
 * @author Aaric, created on 2019-12-04T15:35.
 * @version 1.4.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名用户信息
        UserInfo userInfo = userInfoService.getUserInfo(username);

        // 失败情况一：用户不存在
        if (ObjectUtils.isEmpty(userInfo)) {
            String msg = String.format("用户名%s不存在", username);
            log.info(msg);
            throw new UsernameNotFoundException(msg);
        }

        // 失败情况二：用户被锁定
        if (userInfo.getIsLock()) {
            String msg = String.format("用户名%s被锁定", username);
            log.info(msg);
            throw new LockedException(msg);
        }

        // 失败情况三：用户被锁定或者标记为删除
        if (userInfo.getIsDel()) {
            String msg = String.format("用户名%s被禁用", username);
            log.info(msg);
            throw new DisabledException(msg);
        }

        // 查询当前用户授权信息
        Collection<GrantedAuthority> authorities = queryGrantedAuthorities(userInfo.getId());
        return new LoginSuccessInfo(userInfo.getId(), userInfo.getUsername(), userInfo.getSecret(), authorities);
    }

    /**
     * 根据用户ID查询授权信息
     *
     * @param userId 用户ID
     * @return
     */
    private Collection<GrantedAuthority> queryGrantedAuthorities(Integer userId) {
        // 权限注解示例：@PreAuthorize("hasAuthority('demo:stdCrud:query')")
        List<String> authorities = userInfoService.queryAuthorityList(userId);
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }
}
