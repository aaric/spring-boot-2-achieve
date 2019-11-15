package io.sparrow.sb2.base.api;

import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.pojo.dto.RegionDto;
import com.incarcloud.pojo.entity.Region;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 行政区模块API
 *
 * @author Aaric, created on 2019-11-14T11:17.
 * @version 0.0.1-SNAPSHOT
 */
@Api(tags = "行政区模块API")
public interface RegionApi {

    @ApiOperation(value = "查询省市区结构树")
    ResponseData<List<RegionDto>> list() throws ApiException;

    @ApiOperation(value = "根据ID查询省市区详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "int", paramType = "path", required = true, example = "1")
    })
    ResponseData<Region> get(Integer id) throws ApiException;

//    @ApiOperation(value = "分页查询省市区信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "int", paramType = "path", required = true, example = "1"),
//            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "path", required = true, example = "10")
//    })
//    ResponseData<List<Region>> list(Integer pageNum, Integer pageSize) throws ApiException;
}
