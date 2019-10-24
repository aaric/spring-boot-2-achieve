package com.incarcloud.common.share.map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基站数据（非CDMA格式）
 *
 * @author Aaric, created on 2019-10-14T14:59.
 * @version 1.1.0-SNAPSHOT
 */
public class BsData {

    /**
     * MCC：移动国家代码（中国的为460）
     */
    private int mcc = 460;

    /**
     * MNC：移动网络号码（中国移动为00，中国联通为01）
     */
    private int mnc;

    /**
     * LAC：位置区域码
     */
    private int lac;

    /**
     * CID：基站编号，是个16位的数据（范围是0到65535）
     */
    private int cid;

    /**
     * 默认构造函数
     */
    public BsData() {
    }

    /**
     * 构造函数
     *
     * @param mcc 移动国家代码
     * @param mnc 移动网络号码
     * @param lac 位置区域码
     * @param cid 基站编号
     */
    public BsData(int mcc, int mnc, int lac, int cid) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.cid = cid;
    }

    /**
     * 格式化基站数据为指定格式字符串，例如：460,0,34860,62041
     *
     * @return
     */
    public String format() {
        return StringUtils.joinWith(",", this.mcc, this.mnc, this.lac, this.cid);
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
