package com.incarcloud.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 日志记录
 *
 * @author Aaric, created on 2019-11-08T17:52.
 * @version 1.3.0-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true) //支持setXxx链式编程习惯
@NoArgsConstructor
@KeySequence(value = "log_id_seq")
@TableName(value = "log")
public class Log {

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(position = 1, value = "ID", required = true)
    private Long id;

    @ApiModelProperty(position = 2, value = "标签，即系统名称", required = true)
    private String tagName;

    @ApiModelProperty(position = 3, value = "分组名称，default-操作日志", required = true)
    private String groupName;

    @ApiModelProperty(position = 4, value = "标题，即模块名称", required = true)
    private String title;

    @ApiModelProperty(position = 5, value = "日志内容", required = true)
    private String content;

    @ApiModelProperty(position = 6, value = "备注，附加信息")
    private String remark;

    @ApiModelProperty(position = 7, value = "提交者，即用户名", required = true)
    private String submit;

    @ApiModelProperty(position = 8, value = "HTTP请求地址", required = true)
    private String httpUrl;

    @ApiModelProperty(position = 9, value = "HTTP请求状态码", required = true)
    private Integer httpStatus;

    @ApiModelProperty(position = 10, value = "HTTP请求类型", required = true)
    private String httpType;

    @ApiModelProperty(position = 11, value = "客户端建立连接时间")
    private Timestamp clientConnectStart;

    @ApiModelProperty(position = 12, value = "服务端处理业务开始时间", required = true)
    private Timestamp serverProcessStart;

    @ApiModelProperty(position = 13, value = "服务端处理业务结束时间")
    private Timestamp serverProcessEnd;

    @ApiModelProperty(position = 14, value = "客户端建立连接耗时，单位：ms", required = true)
    private Integer clientConnectInterval;

    @ApiModelProperty(position = 15, value = "服务端处理业务耗时，单位：ms", required = true)
    private Integer serverProcessInterval;

    @ApiModelProperty(position = 16, value = "异常信息，方便排除问题", required = true)
    private String exceptionDetail;

    @ApiModelProperty(position = 17, value = "入库时间", required = true)
    private Date insertTime;
}
