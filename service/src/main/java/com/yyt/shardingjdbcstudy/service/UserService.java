package com.yyt.shardingjdbcstudy.service;

import com.yyt.shardingjdbcstudy.entity.Order;
import com.yyt.shardingjdbcstudy.entity.User;

/**
 * @author yangxin
 * @date 2021/9/14
 */
public interface UserService {

    void create(User user, Order order);

}
