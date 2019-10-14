package com.incarcloud.common.share.map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 地理位置信息
 *
 * @author Aaric, created on 2019-09-04T17:32.
 * @since 0.13.0-SNAPSHOT
 */
public class GeoPoint {

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 纬度
     */
    private float altitude;

    /**
     * 默认构造函数
     */
    public GeoPoint() {
    }

    /**
     * 构造函数
     *
     * @param longitude 经度
     * @param latitude  纬度
     */
    public GeoPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * 构造函数
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param altitude  纬度
     */
    public GeoPoint(double longitude, double latitude, float altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
