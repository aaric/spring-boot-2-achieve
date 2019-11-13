package com.incarcloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incarcloud.base.mapper.RegionMapper;
import com.incarcloud.base.service.RegionService;
import com.incarcloud.pojo.entity.Region;
import org.springframework.stereotype.Service;

/**
 * 行政区信息服务实现
 *
 * @author Aaric, created on 2019-11-12T16:22.
 * @version 1.3.1-SNAPSHOT
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public void initDbData() {
        // 抽取pca-code.json里面的json数据到Postgres
        Region region = new Region("11", "北京市");
        baseMapper.insert(region);
    }
}
