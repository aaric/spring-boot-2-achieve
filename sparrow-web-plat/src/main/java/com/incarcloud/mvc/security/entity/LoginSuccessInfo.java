package com.incarcloud.mvc.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录成功信息
 *
 * @author Aaric, created on 2019-12-04T17:14.
 * @version 1.4.1-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "登录成功信息")
public class LoginSuccessInfo implements UserDetails {

    @ApiModelProperty(position = 1, value = "ID", required = true)
    private Integer id;

    @ApiModelProperty(position = 2, value = "用户名", required = true)
    private String username;

    @ApiModelProperty(position = 3, value = "认证凭据")
    private String password;

    @ApiModelProperty(position = 4, value = "账号拥有权限列表")
    private Collection<? extends GrantedAuthority> authorities;

    public LoginSuccessInfo(Integer id, String username, String password, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * 账号是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
