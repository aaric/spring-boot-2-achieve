package com.incarcloud.sb2.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 登录用户信息DTO
 *
 * @author Aaric, created on 2019-06-28T15:52.
 * @since 0.2.1-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
public class LoginUserDto {

    @NotBlank(message = "{valid.login.user.username.not.blank}") //用户名不能为空
    @Min(value = 5, message = "{valid.login.user.username.min5}") //用户名最少5个字符
    @Max(value = 10, message = "${{valid.login.user.username.max10}}") //用户名最多10个字符
    @ApiModelProperty(position = 1, value = "用户名", example = "root", required = true)
    private String username;

    @Min(value = 8, message = "{valid.login.user.password.min8}") //密码最少8个字符
    @Max(value = 32, message = "${{valid.login.user.password.max32}}") //密码最多32个字符
    @ApiModelProperty(position = 2, value = "登录密码", example = "root", required = true)
    private String password;

    @Email(message = "{valid.login.user.email}") //邮箱格式不正确
    @ApiModelProperty(position = 2, value = "登录密码", example = "root", required = true)
    private String email;

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
