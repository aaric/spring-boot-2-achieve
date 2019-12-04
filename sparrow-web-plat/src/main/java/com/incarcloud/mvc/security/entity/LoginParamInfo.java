package com.incarcloud.mvc.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 登录参数信息
 *
 * @author Aaric, created on 2019-08-01T17:16.
 * @version 1.4.1-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "登录参数信息")
public class LoginParamInfo {

    @ApiModelProperty(position = 1, value = "用户名", example = "root", required = true)
    private String u;

    @ApiModelProperty(position = 2, value = "认证凭据", example = "root", required = true)
    private String p;

    @ApiModelProperty(position = 3, value = "校验字符串", example = "NA", required = true)
    private String v;

    public LoginParamInfo(String u, String p) {
        this.u = u;
        this.p = p;
    }
}
