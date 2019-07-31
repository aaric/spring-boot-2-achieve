package com.incarcloud.sb2.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登录用户服务类
 *
 * @author Aaric, created on 2019-07-31T11:43.
 * @since 0.6.0-SNAPSHOT
 */
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setU("admin");
        userDto.setP("admin");
        userDto.setTs(System.currentTimeMillis());
        userDto.setV("NA");

        userDto.setRoleList(null);

        return null;
    }
}
