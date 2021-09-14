package com.yyt.shardingjdbcstudy.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyt.shardingjdbcstudy.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public interface OrderMapper extends BaseMapper<Order> {

    Integer insertBatch(@Param("list") List<Order> orderList);

}
