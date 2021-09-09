package com.yyt.shadingjdbcstudy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyt.shadingjdbcstudy.entity.Order;
import com.yyt.shadingjdbcstudy.mapper.OrderMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public class ShardingTest extends ShadingJdbcStudyApplicationTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void insert() {
        for (int i = 0, j = 100; i < 100; i++, j++) {
            Order order = new Order();
            order.setUserId((long) j);
            order.setCreateTime(new Date());
            orderMapper.insert(order);
        }
    }

    /**
     * 由于是通过order_id分表，user_id分库，直接通过order_id查询的话那么就会走两条SQL，一条是ds0的t_order_0 一条是ds1的t_order_0
     */
    @Test
    public void select1() {
        Order order = orderMapper.selectById(1435783692574375938L);
        System.out.println(order);
    }

    /**
     * 由于是通过order_id分表，user_id分库.
     * 通过order_id和user_id查询那么直接可以定位到具体的数据库和数据表，那么只会有一条SQL
     */
    @Test
    public void select2() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", 1435783692574375938L);
        queryWrapper.eq("user_id", 39L);
        Order order = orderMapper.selectOne(queryWrapper);
        System.out.println(order);
    }

    @Test
    public void select3() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("create_time", new Date());
        queryWrapper.orderByDesc("user_id");
        List<Order> orders = orderMapper.selectList(queryWrapper);
        System.out.println(orders);
    }

    @Test
    public void select4() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("create_time", new Date());
        queryWrapper.orderByDesc("user_id");
        Page<Order> page = orderMapper.selectPage(new Page<>(2, 10), queryWrapper);
        System.out.println(page.getRecords().size());
    }

    @Test
    public void select5() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("order_id", 100L);
        queryWrapper.ge("order_id", 90L);
        queryWrapper.orderByDesc("create_time");
        Page<Order> page = orderMapper.selectPage(new Page<>(1, 5), queryWrapper);
        System.out.println(page.getRecords().size());
    }
}
