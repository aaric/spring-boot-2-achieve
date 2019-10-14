package com.incarcloud.common.share.map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基站定位结果（非CDMA格式），参考http://www.gpsspg.com/api/bs/
 *
 * @author Aaric, created on 2019-10-14T15:19.
 * @since 1.1.0-SNAPSHOT
 */
public class BsResult extends GeoPoint {

    /**
     * 结果ID
     */
    private String id;

    /**
     * 覆盖半径参考值，如：1500 米。
     */
    private Integer radius;

    /**
     * 靠近地址描述
     */
    private String address;

    /**
     * 靠近道路描述
     */
    private String roads;

    /**
     * 6位行政区划代码，见国家统计局《县及县以上行政区划代码》。
     */
    private String rid;

    /**
     * 12位区划代码（参考值），见国家统计局《统计用区划代码和城乡划分代码》。
     */
    private String rids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRoads() {
        return roads;
    }

    public void setRoads(String roads) {
        this.roads = roads;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRids() {
        return rids;
    }

    public void setRids(String rids) {
        this.rids = rids;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
