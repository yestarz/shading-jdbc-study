package com.yyt.shardingjdbcstudy.service.impl;

import com.yyt.shardingjdbcstudy.config05.dynamic.Sharding;
import com.yyt.shardingjdbcstudy.dao.mapper.OrderMapper;
import com.yyt.shardingjdbcstudy.entity.Order;
import com.yyt.shardingjdbcstudy.service.OrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    @Sharding
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    @Sharding
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(Order order) {
        orderMapper.insert(order);
    }

    @Override
    @Sharding
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @ShardingTransactionType(TransactionType.XA)
    public void insert2(Order order) {
        orderMapper.insert(order);
        orderMapper.insert(order);
        if (1 == 1) {
            throw new RuntimeException("error!");
        }
    }
}
