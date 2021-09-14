import com.yyt.shardingjdbcstudy.dao.mapper.OrderMapper;
import com.yyt.shardingjdbcstudy.entity.Order;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangxin
 * @date 2021/9/14
 */
public class Config02Test extends BaseTest {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void test1() {
        System.out.println(orderMapper.selectList(null));
    }

    @Test
    public void testInsert() {
        for (long i = 0; i < 100; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setCreateTime(new Date());
            orderMapper.insert(order);
        }
    }

    @Test
    public void testBatchInsert() {
        List<Order> orderList = new ArrayList<>();
        for (long i = 0; i < 100; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setCreateTime(new Date());
            orderList.add(order);
        }
        orderMapper.insertBatch(orderList);
    }
}
