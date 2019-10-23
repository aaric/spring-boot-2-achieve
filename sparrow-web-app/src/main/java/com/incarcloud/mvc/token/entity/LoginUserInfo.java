package com.incarcloud.mvc.token.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 登录用户信息
 *
 * @author Aaric, created on 2019-08-01T17:16.
 * @version 0.7.0-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
public class LoginUserInfo {

    @ApiModelProperty(position = 1, value = "用户名", example = "user", required = true)
    private String u;

    @ApiModelProperty(position = 2, value = "密码", example = "user", required = true)
    private String p;

    @ApiModelProperty(position = 3, value = "时间戳", example = "123456", required = true)
    private Long ts;

    @ApiModelProperty(position = 4, value = "校验字符串", example = "NA", required = true)
    private String v;

    public LoginUserInfo() {
    }

    public LoginUserInfo(String u, String p) {
        this.u = u;
        this.p = p;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
