package com.incarcloud.common.map;

import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsResult;

/**
 * 基站位置服务接口(gpsspg.com)
 *
 * @author Aaric, created on 2019-10-14T14:15.
 * @since 1.1.0-SNAPSHOT
 */
public interface SpgSevice {

    /**
     * 查询基站定位逆地理编码地址信息
     *
     * @param data 基站数据
     * @return
     * @throws Exception
     */
    BsResult getReGeoInfo(BsData... data) throws Exception;
}
