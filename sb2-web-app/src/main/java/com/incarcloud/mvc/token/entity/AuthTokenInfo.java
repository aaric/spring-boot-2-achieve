package com.incarcloud.mvc.token.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 授权Token信息
 *
 * @author Aaric, created on 2019-08-09T13:56.
 * @since 0.7.0-SNAPSHOT
 */
@ApiModel(description = "授权Token信息")
public class AuthTokenInfo {

    @ApiModelProperty(position = 1, value = "用户名", required = true)
    private String cid;

    @ApiModelProperty(position = 2, value = "用户名", required = true)
    private String token;

    public AuthTokenInfo() {
    }

    public AuthTokenInfo(String cid, String token) {
        this.cid = cid;
        this.token = token;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
