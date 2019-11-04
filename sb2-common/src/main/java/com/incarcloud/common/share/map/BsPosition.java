package com.incarcloud.common.share.map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基站定位结果（非CDMA格式），参考http://www.gpsspg.com/api/bs/
 *
 * @author Aaric, created on 2019-10-14T15:19.
 * @version 1.1.0-SNAPSHOT
 */
public class BsPosition extends GeoPoint {

    /**
     * 覆盖半径参考值，如：80 米。
     */
    private Integer radius;

    /**
     * 靠近地址描述
     */
    private String address;

    /**
     * 靠近道路描述
     */
    private String road;

    /**
     * 6位行政区划代码，见国家统计局《县及县以上行政区划代码》。
     */
    private String code6;

    /**
     * 12位区划代码（参考值），见国家统计局《统计用区划代码和城乡划分代码》。
     */
    private String code12;

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCode6() {
        return code6;
    }

    public void setCode6(String code6) {
        this.code6 = code6;
    }

    public String getCode12() {
        return code12;
    }

    public void setCode12(String code12) {
        this.code12 = code12;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
