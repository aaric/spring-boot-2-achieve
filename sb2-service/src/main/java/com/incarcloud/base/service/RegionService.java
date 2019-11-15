package com.incarcloud.base.service;

import com.incarcloud.common.exception.ApiException;
import com.incarcloud.pojo.dto.RegionDto;
import com.incarcloud.pojo.entity.Region;

import java.util.List;

/**
 * 行政区信息服务接口
 *
 * @author Aaric, created on 2019-11-12T16:22.
 * @version 1.3.1-SNAPSHOT
 */
public interface RegionService {

    /**
     * 保存省市区信息
     *
     * @param parentId    父节点ID
     * @param rank        所在层级: 1-省, 2-市, 3-区
     * @param pcaCode     行政区代码
     * @param pcaName     行政区名称
     * @param pcaCodePath 行政区代码路径，以“/”分割
     * @param pcaNamePath 行政区名称路径，以“/”分割
     * @return 返回主键ID
     */
    Integer savePca(Integer parentId, Integer rank, String pcaCode, String pcaName, String pcaCodePath, String pcaNamePath);

    /**
     * 查询省市区结构树
     *
     * @return
     * @throws ApiException
     */
    List<RegionDto> queryPacTreeList() throws ApiException;

    /**
     * 根据ID查询省市区详情
     *
     * @param id 主键ID
     * @return
     * @throws ApiException
     */
    Region get(Integer id) throws ApiException;
}
