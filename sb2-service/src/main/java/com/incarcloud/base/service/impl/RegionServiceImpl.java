package com.incarcloud.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incarcloud.base.mapper.RegionMapper;
import com.incarcloud.base.service.RegionService;
import com.incarcloud.common.exception.ApiException;
import com.incarcloud.pojo.dto.RegionDto;
import com.incarcloud.pojo.entity.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 行政区信息服务实现
 *
 * @author Aaric, created on 2019-11-12T16:22.
 * @version 1.3.1-SNAPSHOT
 */
@Slf4j
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public Integer savePca(Integer parentId, Integer rank, String pcaCode, String pcaName, String pcaCodePath, String pcaNamePath) {
        // 设置关键信息
        Region region = new Region(rank, pcaCode, pcaName);
        region.setParentId(parentId);
        region.setPcaCodePath(pcaCodePath);
        region.setPcaNamePath(pcaNamePath);

        // 保存到数据库
        baseMapper.insert(region);

        // 返回主键ID
        return region.getId();
    }

    @Override
    public List<RegionDto> queryPacTreeList() throws ApiException {
        // 查询区级数据集合
        Map<Integer, List<RegionDto>> areaListMap = null;
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Region::getRank, Region.RANK_AREA); //县级（区县）
        List<Region> areaList = baseMapper.selectList(wrapper);
        if (null != areaList && 0 != areaList.size()) {
            // 封装区级数据集合
            areaListMap = areaList.stream()
                    .map(area -> new RegionDto(area.getId(), area.getParentId(), area.getPcaCode(), area.getPcaName()))
                    .collect(Collectors.groupingBy(RegionDto::getParentId));
        }

        // 查询市级数据集合
        Map<Integer, List<RegionDto>> cityListMap = null;
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Region::getRank, Region.RANK_CITY); //地级（城市）
        List<Region> cityList = baseMapper.selectList(wrapper);
        if (null != cityList && 0 != cityList.size()) {
            // 给市级数据设置子节点集合
            RegionDto temp;
            List<RegionDto> cityDtoList = new ArrayList<>();
            for (Region city : cityList) {
                temp = new RegionDto(city.getId(), city.getParentId(), city.getPcaCode(), city.getPcaName());
                temp.setChildren(areaListMap.get(city.getId()));
                cityDtoList.add(temp);
            }

            // 封装市级数据集合
            cityListMap = cityDtoList.stream().collect(Collectors.groupingBy(RegionDto::getParentId));
        }

        // 查询省级数据集合
        List<RegionDto> rootList = null;
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Region::getRank, Region.RANK_PROVINCE); //省级（省份、直辖市、自治区）
        List<Region> provinceList = baseMapper.selectList(wrapper);
        if (null != provinceList && 0 != provinceList.size()) {
            // 给市级数据设置子节点集合
            RegionDto temp;
            rootList = new ArrayList<>();
            for (Region province : provinceList) {
                temp = new RegionDto(province.getId(), province.getParentId(), province.getPcaCode(), province.getPcaName());
                temp.setChildren(cityListMap.get(province.getId()));
                rootList.add(temp);
            }
        }

        return rootList;
    }

    @Override
    public Region get(Integer id) throws ApiException {
        return baseMapper.selectById(id);
    }
}
