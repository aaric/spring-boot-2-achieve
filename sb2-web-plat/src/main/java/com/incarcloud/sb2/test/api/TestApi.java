package com.incarcloud.sb2.test.api;

import com.incarcloud.sb2.dto.user.LoginUserDto;
import com.incarcloud.sb2.security.LoginUserInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 测试框架模块API
 *
 * @author Aaric, created on 2019-06-26T15:35.
 * @since 0.2.1-SNAPSHOT
 */
@Api(tags = "测试框架模块API")
public interface TestApi {

    @ApiOperation(value = "验证国际化配置")
    Map<String, Object> i18n();

    @ApiOperation(value = "验证数据校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginUserDto", value = "登录用户信息", dataType = "LoginUserDto", paramType = "body", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "0001-数据校验失败")
    })
    Map<String, Object> validate(LoginUserDto loginUserDto);

    @ApiOperation(value = "根据ID返回登录用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "登录用户信息ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    Map<String, Object> get(Integer id);

    @ApiOperation(value = "安全框架->处理跳转")
    Map<String, Object> authRedirect();

    @ApiOperation(value = "安全框架->登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginUserInfo", value = "登录用户信息", dataType = "LoginUserInfo", paramType = "body", required = true)
    })
    @PostMapping("/authLogin")
    default void authLogin(@RequestBody LoginUserInfo loginUserInfo) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    };

    @ApiOperation(value = "安全框架->登出")
    @PostMapping("/authLogout")
    default void authLogout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    };
}
