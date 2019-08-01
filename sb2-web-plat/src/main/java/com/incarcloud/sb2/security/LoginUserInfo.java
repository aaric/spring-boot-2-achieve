package com.incarcloud.sb2.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 登录用户信息
 *
 * @author Aaric, created on 2019-08-01T17:16.
 * @since 0.6.0-SNAPSHOT
 */
@ApiModel(description = "登录用户信息")
public class LoginUserInfo {

    @ApiModelProperty(position = 1, value = "用户名", example = "admin", required = true)
    private String u;

    @ApiModelProperty(position = 1, value = "密码", example = "root", required = true)
    private String p;

    @ApiModelProperty(position = 1, value = "时间戳", example = "12346", required = true)
    private Long ts;

    @ApiModelProperty(position = 1, value = "校验字符串", example = "NA", required = true)
    private String v;

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
