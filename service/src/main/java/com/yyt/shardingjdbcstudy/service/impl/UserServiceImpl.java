package com.yyt.shardingjdbcstudy.service.impl;

import com.yyt.shardingjdbcstudy.dao.mapper.UserMapper;
import com.yyt.shardingjdbcstudy.entity.Order;
import com.yyt.shardingjdbcstudy.entity.User;
import com.yyt.shardingjdbcstudy.service.OrderService;
import com.yyt.shardingjdbcstudy.service.UserService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderService orderService;

    @Override
    @Transactional
    public void create(User user, Order order) {
        userMapper.insert(user);
        orderService.insert(order);
    }
}
