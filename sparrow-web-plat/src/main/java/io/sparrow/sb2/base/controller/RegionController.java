package io.sparrow.sb2.base.controller;

import com.incarcloud.base.service.RegionService;
import com.incarcloud.common.data.ResponseData;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.pojo.dto.RegionDto;
import com.incarcloud.pojo.entity.Region;
import io.sparrow.sb2.base.api.RegionApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区模块API
 *
 * @author Aaric, created on 2019-11-14T11:18.
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/plat/base/region")
public class RegionController implements RegionApi {

    @Autowired
    private RegionService regionService;

    @Override
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<List<RegionDto>> list() throws ApiException {
        return ResponseData.ok(regionService.queryPacTreeList());
    }

    @Override
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData<Region> get(@PathVariable Integer id) throws ApiException {
        return ResponseData.ok(regionService.get(id));
    }
}
