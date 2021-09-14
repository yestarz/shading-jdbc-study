package com.yyt.shardingjdbcstudy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author yangxin
 * @date 2021/9/9
 */
@Data
@TableName(value = "t_order")
public class Order {

    /**
     * 这里要注意：如果不指定type的话，默认是mybatisPlus的雪花算法生成器。如果想使用sharding-jdbc的，则需要指定为AUTO
     */
    @TableId(value = "order_id",type = IdType.AUTO)
    private Long orderId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "create_time")
    private Date createTime;
}
