package io.sparrow.sb2.auth.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.mvc.token.entity.AuthTokenInfo;
import com.incarcloud.mvc.token.entity.LoginUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录授权模块API
 *
 * @author Aaric, created on 2019-08-09T13:52.
 * @version 0.7.0-SNAPSHOT
 */
@Api(tags = "登录授权模块API")
public interface AuthApi {

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginUserInfo", value = "登录用户信息", dataType = "LoginUserInfo", paramType = "body", required = true)
    })
    ResponseData<AuthTokenInfo> login(LoginUserInfo loginUserInfo, HttpServletRequest request);

    @ApiOperation("注销登录")
    ResponseData<Object> logout();
}
