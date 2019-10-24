package io.sparrow.sb2.demo.api;

import com.incarcloud.common.data.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
}
