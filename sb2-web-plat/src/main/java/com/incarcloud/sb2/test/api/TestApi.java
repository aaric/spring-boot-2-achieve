package com.incarcloud.sb2.test.api;

import com.incarcloud.sb2.dto.user.LoginUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
    Map<String, Object> validate(LoginUserDto loginUserDto);

    @ApiOperation(value = "根据ID返回登录用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "登录用户信息ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    Map<String, Object> get(Integer id);
}