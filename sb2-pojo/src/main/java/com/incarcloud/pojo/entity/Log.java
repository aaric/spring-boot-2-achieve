package com.incarcloud.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 日志记录
 *
 * @author Aaric, created on 2019-11-08T17:52.
 * @version 1.3.0-SNAPSHOT
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor //支持setXxx链式编程习惯
@KeySequence(value = "log_id_seq")
@TableName(value = "log")
public class Log {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 标签，即系统名称
     */
    private String tag;

    /**
     * 标题，即模块名称
     */
    private String title;

    /**
     * 日志内容
     */
    private String content;
}
