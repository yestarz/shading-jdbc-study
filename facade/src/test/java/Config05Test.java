import com.yyt.shardingjdbcstudy.entity.Order;
import com.yyt.shardingjdbcstudy.entity.User;
import com.yyt.shardingjdbcstudy.service.OrderService;
import com.yyt.shardingjdbcstudy.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yangxin
 * @date 2021/9/14
 */
public class Config05Test extends BaseTest {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Test
    public void test1() {
        System.out.println(orderService.selectById(12L));
    }

    @Test
    public void test2(){
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(1L);
        orderService.insert(order);
    }


    @Test
    public void test3(){
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(1L);

        User user = new User();
        user.setAge(18);
        user.setName("test1");
        user.setCreateTime(new Date());

        userService.create(user, order);
    }

    /**
     * 测试分布式事务
     */
    @Test
    public void test4(){
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(2L);
        orderService.insert2(order);
    }
}
