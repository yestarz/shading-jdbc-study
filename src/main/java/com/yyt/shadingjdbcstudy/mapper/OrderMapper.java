package com.yyt.shadingjdbcstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyt.shadingjdbcstudy.entity.Order;

import java.util.List;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public interface OrderMapper extends BaseMapper<Order> {

    Integer insertBatch(List<Order> orderList);

}
