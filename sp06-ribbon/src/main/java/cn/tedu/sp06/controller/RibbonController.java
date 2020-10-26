package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RibbonController {
    @Autowired
    private RestTemplate rt;

    //调用远程的商品服务
    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        //{1}时RestTemplate定义的一种占位符格式，后面第三个参数orderId会对占位符进行填充
        return rt.getForObject("http://item-service/{1}", JsonResult.class,orderId);
    }

    //调用商品服务，减少商品库存
    @PostMapping("/item-service/decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        //发送 post 请求
        //items 是请求协议的具体数据
        return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }

    @GetMapping("/user-service/{userId}/score")
    public JsonResult addScore(
            @PathVariable Integer userId, Integer score) {
        return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
    }

    @GetMapping("/order-service")
    public JsonResult addOrder() {
        return rt.getForObject("http://order-service/", JsonResult.class);
    }
}
