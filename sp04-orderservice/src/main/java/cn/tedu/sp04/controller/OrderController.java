package cn.tedu.sp04.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        log.info("获取订单,orderId="+orderId);
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok(order);
    }

    @GetMapping("/")
    public JsonResult<?> addOrder(){
        Order order=new Order();
        order.setId("123abc");
        order.setUser(new User(8,"用户8","密码8"));
        order.setItems(Arrays.asList(new Item(1,"aaa",6),
                new Item(2,"aba",7),
                new Item(3,"aac",8),
                new Item(4,"caa",9),
                new Item(5,"abc",10)));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");
    }
}
