package com.incarcloud.sb2.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录用户信息DTO
 *
 * @author Aaric, created on 2019-06-28T15:52.
 * @since 0.2.1-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
public class LoginUserDto {

    @ApiModelProperty(position = 1, value = "用户名", example = "root", required = true)
    private String username;

    @ApiModelProperty(position = 1, value = "登录密码", example = "root", required = true)
    private String password;

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
