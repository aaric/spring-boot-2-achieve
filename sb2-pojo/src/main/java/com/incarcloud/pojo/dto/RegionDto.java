package com.incarcloud.pojo.dto;

import com.incarcloud.pojo.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 行政区信息DTO
 *
 * @author Aaric, created on 2019-11-14T11:51.
 * @version 1.3.2-SNAPSHOT
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(description = "行政区信息")
public class RegionDto extends Region {

    @ApiModelProperty(position = 9, value = "子节点集合")
    private List<RegionDto> children;

    public RegionDto(Integer id, String pcaCode, String pcaName) {
        this.setId(id);
        this.setPcaCode(pcaCode);
        this.setPcaName(pcaName);
    }

    public RegionDto(Integer id, Integer parentId, String pcaCode, String pcaName) {
        this.setId(id);
        this.setParentId(parentId);
        this.setPcaCode(pcaCode);
        this.setPcaName(pcaName);
    }
}
