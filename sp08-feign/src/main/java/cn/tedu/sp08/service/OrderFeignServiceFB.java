package cn.tedu.sp08.service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

/**
 * @author wxh
 * @date 2020/10/28 10:12
 * @description
 */
@Component
public class OrderFeignServiceFB implements OrderFeignService{
    @Override
    public JsonResult<Order> getOrder(String orderId) {
        return JsonResult.err("无法获取商品订单");
    }

    @Override
    public JsonResult addOrder() {
        return JsonResult.err("无法保存订单");
    }
}
