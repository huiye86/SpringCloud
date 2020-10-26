package cn.tedu.sp04.service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    //TODO:远程调用商品服务，获取商品
    //TODO:远程调用用户，获取用户数据
    @Override
    public Order getOrder(String orderId) {
        Order order=new Order();
        order.setId(orderId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("保存订单信息"+order);
    }
}
