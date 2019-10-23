package io.sparrow.sb2.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 登录用户信息DTO
 *
 * @author Aaric, created on 2019-06-28T15:52.
 * @version 0.2.1-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ValidateUserDto {

    @NotBlank(message = "{validate.login-user.username.not-blank}") //用户名不能为空
    @Size(min = 5, max = 10, message = "{validate.login-user.username.size}") //用户名要求5-10个字符
    @ApiModelProperty(position = 1, value = "用户名", example = "root", required = true)
    @Getter
    @Setter
    @NonNull
    private String username;

    @Size(min = 5, max = 10, message = "{validate.login-user.password.size}") //密码要求8-32个字符
    @ApiModelProperty(position = 2, value = "登录密码", example = "root", required = true)
    @Getter
    @Setter
    @NonNull
    private String password;

    @Email(message = "{validate.login-user.email.error}") //邮箱格式不正确
    @ApiModelProperty(position = 2, value = "登录密码", example = "root", required = true)
    @Getter
    @Setter
    @NonNull
    private String email;
}
