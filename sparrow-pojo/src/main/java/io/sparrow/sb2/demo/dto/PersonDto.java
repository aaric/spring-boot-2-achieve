package io.sparrow.sb2.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 人员DTO
 *
 * @author Aaric, created on 2019-06-28T15:52.
 * @version 0.2.1-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
@ToString
public class PersonDto {

    @Getter
    @Setter
    @NotBlank(message = "{validate.login-user.username.not-blank}") //姓名不能为空
    @ApiModelProperty(position = 1, value = "姓名", example = "jenkins", required = true)
    private String fullName;

    @Getter
    @Setter
    @Email(message = "{validate.login-user.email.error}") //邮箱格式不正确
    @ApiModelProperty(position = 2, value = "联系邮箱", example = "jenkins@incarcloud.com", required = true)
    private String email;

    public PersonDto() {
    }

    public PersonDto(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
