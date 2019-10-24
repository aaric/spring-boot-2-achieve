package io.sparrow.sb2.demo.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 标准增删改查示例API
 *
 * @author Aaric, created on 2019-10-24T10:00.
 * @version 1.1.0-SNAPSHOT
 */
@Api(tags = "标准增删改查示例API")
public interface StdCrudApi {

    @ApiOperation(value = "保存数据")
    ResponseData<Object> save() throws ApiException;

    @ApiOperation(value = "更新数据")
    ResponseData<Object> update() throws ApiException;

    @ApiOperation(value = "查询数据")
    ResponseData<Object> get() throws ApiException;

    @ApiOperation(value = "批量查询数据")
    ResponseData<Object> query() throws ApiException;

    @ApiOperation(value = "删除数据")
    ResponseData<Object> delete() throws ApiException;
}
