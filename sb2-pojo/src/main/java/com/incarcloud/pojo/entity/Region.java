package com.incarcloud.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 行政区信息（省市区三级联动数据）
 *
 * @author Aaric, created on 2019-11-12T15:15.
 * @version 1.3.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@KeySequence(value = "region_id_seq", clazz = Integer.class)
public class Region extends Model<Region> {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 行政区代码
     */
    private String regionCode;

    /**
     * 行政区名称
     */
    private String regionName;

    public Region(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}
