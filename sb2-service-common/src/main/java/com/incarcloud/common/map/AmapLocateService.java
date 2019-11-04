package com.incarcloud.common.map;

import com.incarcloud.common.share.map.BsData;
import com.incarcloud.common.share.map.BsPosition;

import java.util.List;

/**
 * 高德地图服务（基站定位）接口
 *
 * @author Aaric, created on 2019-11-04T11:16.
 * @version 1.2.0-SNAPSHOT
 */
public interface AmapLocateService {

    /**
     * 查询基站定位逆地理编码地址信息
     *
     * @param imei    SIM卡IMEI标识
     * @param bts     接入基站信息
     * @param nearBts 周边基站信息（不含接入基站信息）
     * @return
     * @throws Exception
     */
    BsPosition getAddress(String imei, BsData bts, List<BsData> nearBts) throws Exception;
}
