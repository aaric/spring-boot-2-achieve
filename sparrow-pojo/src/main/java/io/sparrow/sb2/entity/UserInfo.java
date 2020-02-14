package io.sparrow.sb2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户信息
 *
 * @author Aaric, created on 2019-12-04T15:27.
 * @version 1.4.1-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@KeySequence(value = "user_info_id_seq")
@TableName(value = "user_info")
@ApiModel(description = "用户信息")
public class UserInfo {

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(position = 1, value = "ID", required = true)
    private Integer id;

    @ApiModelProperty(position = 2, value = "用户名", required = true)
    private String username;

    @ApiModelProperty(position = 3, value = "密码", required = true)
    private String secret;

    @ApiModelProperty(position = 4, value = "密码盐", required = true)
    private String secretSalt;

    @ApiModelProperty(position = 5, value = "是否锁定，默认false", required = true)
    private Boolean isLock;

    @ApiModelProperty(position = 6, value = "是否删除，默认false", required = true)
    private Boolean isDel;

    @ApiModelProperty(position = 7, value = "入库时间", required = true)
    private Date insertTime;
}
