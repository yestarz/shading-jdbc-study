package com.yyt.shardingjdbcstudy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@TableName("t_user")
@Data
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "create_time")
    private Date createTime;

}
