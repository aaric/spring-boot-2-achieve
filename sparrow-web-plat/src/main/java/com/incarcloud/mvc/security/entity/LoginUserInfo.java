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
 * 登录用户信息
 *
 * @author Aaric, created on 2019-08-01T17:16.
 * @version 0.6.0-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "登录用户信息")
public class LoginUserInfo implements UserDetails {

    @ApiModelProperty(position = 1, value = "用户名", example = "root", required = true)
    private String u;

    @ApiModelProperty(position = 2, value = "密码", example = "root", required = true)
    private String p;

    @ApiModelProperty(position = 3, value = "时间戳", example = "123456", required = true)
    private Long ts;

    @ApiModelProperty(position = 4, value = "校验字符串", example = "NA", required = true)
    private String v;

    public LoginUserInfo(String u, String p) {
        this.u = u;
        this.p = p;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getP();
    }

    @Override
    public String getUsername() {
        return getU();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
