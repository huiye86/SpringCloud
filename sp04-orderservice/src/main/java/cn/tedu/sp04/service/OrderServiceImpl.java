package cn.tedu.sp04.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemFeignService itemFeignService;
    @Autowired
    private UserFeignService userFeignService;
    //TODO:远程调用商品服务，获取商品
    //TODO:远程调用用户，获取用户数据
    @Override
    public Order getOrder(String orderId) {
        //调用user-service获取用户信息
        JsonResult<User> user=userFeignService.getUser(7);

        //调用item-service获取商品信息
        JsonResult<List<Item>> items=itemFeignService.getItems(orderId);

        Order order=new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    @Override
    public void addOrder(Order order) {
        itemFeignService.decreaseNumber(order.getItems());
        userFeignService.addScore(7, 100);

        log.info("保存订单信息"+order);
    }
}
