package io.sparrow.sb2.demo.api;

import com.incarcloud.common.data.ResponseData;
import io.sparrow.sb2.demo.dto.ValidateUserDto;
import io.swagger.annotations.*;

/**
 * 测试框架模块API
 *
 * @author Aaric, created on 2019-06-26T15:35.
 * @version 0.2.1-SNAPSHOT
 */
@Api(tags = "测试框架模块API")
public interface TestApi {

    @ApiOperation(value = "验证国际化配置")
    ResponseData<Object> i18n();

    @ApiOperation(value = "验证数据校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "validateUserDto", value = "登录用户信息", dataType = "ValidateUserDto", paramType = "body", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "0001-数据校验失败")
    })
    ResponseData<Object> validate(ValidateUserDto validateUserDto);

    @ApiOperation(value = "根据ID返回登录用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "登录用户信息ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    ResponseData<ValidateUserDto> get(Integer id);
}
