package com.yyt.shardingjdbcstudy.service;


import com.yyt.shardingjdbcstudy.entity.Order;

/**
 * @author yangxin
 * @date 2021/9/14
 */
public interface OrderService {

    Order selectById(Long id);

    void insert(Order order);

    void insert2(Order order);

}
