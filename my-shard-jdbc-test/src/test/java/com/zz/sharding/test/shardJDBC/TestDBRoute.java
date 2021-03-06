package com.zz.sharding.test.shardJDBC;

import com.alibaba.fastjson.JSON;
import com.zz.sharding.jdbc.bean.Order;
import com.zz.sharding.jdbc.dao.OrderMapper;
import com.zz.sharding.test.common.BuildOrderUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhangzuizui
 * @date 2018/3/28 19:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/shard-datasources-mybatis-route.xml"})
public class TestDBRoute {
    public static void main(String[] args) {
        TestDBRoute a = new TestDBRoute();
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(a.getClass().getClassLoader());
        System.out.println(System.getProperty("user.dir"));
    }
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testInsert(){
        List<Order> orderList = BuildOrderUtils.getOrderList();
        for (Order order : orderList){
            orderMapper.insert(order);
        }
    }
    @Test
    public void testSelect(){
        List<Order> orderList = orderMapper.selectByPrimaryKey(1L);
        System.out.println(JSON.toJSONString(orderList));
    }
}
