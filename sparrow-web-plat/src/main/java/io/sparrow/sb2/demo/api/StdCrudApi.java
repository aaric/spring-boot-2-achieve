package io.sparrow.sb2.demo.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import io.sparrow.sb2.dto.PersonDto;
import io.swagger.annotations.*;

import java.util.List;

/**
 * 标准增删改查示例API
 *
 * @author Aaric, created on 2019-10-24T10:00.
 * @version 1.1.0-SNAPSHOT
 */
@Api(tags = "标准增删改查示例API")
public interface StdCrudApi {

    @ApiOperation(value = "保存人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personDto", value = "人员信息", dataType = "PersonDto", paramType = "body", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 21, message = "0021-人员信息校验失败")
    })
    ResponseData<Integer> save(PersonDto personDto) throws ApiException;

    @ApiOperation(value = "更新人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personDto", value = "人员信息", dataType = "PersonDto", paramType = "body", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 21, message = "0021-人员信息校验失败")
    })
    ResponseData<Object> update(PersonDto personDto) throws ApiException;

    @ApiOperation(value = "查询人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "人员信息ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    ResponseData<PersonDto> get(Integer id) throws ApiException;

    @ApiOperation(value = "批量查询人员信息")
    ResponseData<List<PersonDto>> query() throws ApiException;

    @ApiOperation(value = "删除人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "人员信息ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    ResponseData<Object> delete(Integer id) throws ApiException;
}
