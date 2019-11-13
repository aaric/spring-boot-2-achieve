package com.incarcloud.pojo.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 日志记录
 *
 * @author Aaric, created on 2019-11-08T17:52.
 * @version 1.3.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@KeySequence(value = "log_id_seq", clazz = Integer.class)
public class Log extends Model<Log> {

    /**
     * ID
     */
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
