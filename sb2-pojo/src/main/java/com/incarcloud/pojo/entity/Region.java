package com.incarcloud.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 行政区信息（省市区三级联动数据）<br>
 * <i>参考文档：https://github.com/modood/Administrative-divisions-of-China</i>
 *
 * @author Aaric, created on 2019-11-12T15:15.
 * @version 1.3.1-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@KeySequence(value = "region_id_seq", clazz = Integer.class)
@TableName(value = "region")
@ApiModel(description = "行政区信息")
public class Region {

    /**
     * 省级（省份、直辖市、自治区）
     */
    public static final int RANK_PROVINCE = 1;

    /**
     * 地级（城市）
     */
    public static final int RANK_CITY = 2;

    /**
     * 县级（区县）
     */
    public static final int RANK_AREA = 3;

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(position = 1, value = "ID", required = true)
    private Integer id;

    @ApiModelProperty(position = 2, value = "父节点ID", required = true)
    private Integer parentId;

    @ApiModelProperty(position = 3, value = "所在层级: 1-省, 2-市, 3-区", required = true)
    private Integer rank;

    @ApiModelProperty(position = 4, value = "行政区代码", required = true)
    private String pcaCode;

    @ApiModelProperty(position = 5, value = "行政区名称", required = true)
    private String pcaName;

    @ApiModelProperty(position = 6, value = "行政区代码路径，以“/”分割")
    private String pcaCodePath;

    @ApiModelProperty(position = 7, value = "行政区名称路径，以“/”分割")
    private String pcaNamePath;

    @ApiModelProperty(position = 8, value = "入库时间")
    private Date insertTime;

    public Region(Integer rank, String pcaCode, String pcaName) {
        this.rank = rank;
        this.pcaCode = pcaCode;
        this.pcaName = pcaName;
    }
}
