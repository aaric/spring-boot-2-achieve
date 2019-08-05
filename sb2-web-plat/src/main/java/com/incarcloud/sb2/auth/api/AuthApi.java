package com.incarcloud.sb2.auth.api;

import com.incarcloud.mvc.security.entity.LoginUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录授权模块API
 *
 * @author Aaric, created on 2019-08-02T17:58.
 * @since 0.6.0-SNAPSHOT
 */
@Api(tags = "登录授权模块API")
public interface AuthApi {

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginUserInfo", value = "登录用户信息", dataType = "LoginUserInfo", paramType = "body", required = true)
    })
    @PostMapping("/login")
    default void fakeLogin(@RequestBody LoginUserInfo loginUserInfo) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @ApiOperation("注销登录")
    @PostMapping("/logout")
    default void fakeLogout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @ApiOperation("当前登录用户")
    Map<String, Object> current(HttpServletRequest request);
}
