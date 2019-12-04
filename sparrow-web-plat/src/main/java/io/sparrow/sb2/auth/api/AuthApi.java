package io.sparrow.sb2.auth.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.mvc.security.entity.LoginParamInfo;
import com.incarcloud.mvc.security.entity.LoginSuccessInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录授权模块API
 *
 * @author Aaric, created on 2019-08-02T17:58.
 * @version 0.6.0-SNAPSHOT
 */
@Api(tags = "登录授权模块API")
public interface AuthApi {

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginParamInfo", value = "登录参数信息", dataType = "LoginParamInfo", paramType = "body", required = true)
    })
    @PostMapping("/login")
    default LoginSuccessInfo fakeLogin(@RequestBody LoginParamInfo loginParamInfo) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @ApiOperation("注销登录")
    @PostMapping("/logout")
    default void fakeLogout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @ApiOperation("无授权默认跳转地址")
    @ApiResponses({
            @ApiResponse(code = 31, message = "用户未登录")
    })
    ResponseData<String> redirect(HttpServletRequest request) throws ApiException;
}
