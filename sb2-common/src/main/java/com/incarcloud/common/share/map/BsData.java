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
     * MNC：移动网络号码（中国移动为0，中国联通为1）
     */
    private int mnc;

    /**
     * LAC：位置区域码，取值范围：0-65535
     */
    private int lac;

    /**
     * 基站编号，取值范围：0-65535，0-268435455<br>
     * 其中，0,65535,268435455不使用，编号大于65535时为3G基站
     */
    private int cellid;

    /**
     * 信号强度，取值范围：0到-113dbm
     */
    private int signal;

    /**
     * 默认构造函数
     */
    public BsData() {
    }

    /**
     * 构造函数
     *
     * @param mcc    移动国家代码
     * @param mnc    移动网络号码
     * @param lac    位置区域码
     * @param cellid 基站编号
     * @param signal 信号强度
     */
    public BsData(int mcc, int mnc, int lac, int cellid, int signal) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.cellid = cellid;
        this.signal = signal;
    }

    /**
     * 格式化基站数据为指定格式字符串，例如：460,0,34860,62041,-65
     *
     * @return
     */
    public String format() {
        return StringUtils.joinWith(",", this.mcc, this.mnc, this.lac, this.cellid, this.signal);
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

    public int getCellid() {
        return cellid;
    }

    public void setCellid(int cellid) {
        this.cellid = cellid;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
