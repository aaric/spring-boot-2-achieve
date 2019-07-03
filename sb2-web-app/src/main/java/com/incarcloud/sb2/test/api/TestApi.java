package com.incarcloud.sb2.test.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * 测试框架模块API
 *
 * @author Aaric, created on 2019-07-03T12:40.
 * @since 0.3.0-SNAPSHOT
 */
@Api(tags = "测试框架模块API")
public interface TestApi {

    @ApiOperation(value = "验证国际化配置")
    Map<String, Object> i18n();
}
