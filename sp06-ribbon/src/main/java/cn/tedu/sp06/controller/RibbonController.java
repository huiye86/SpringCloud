package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod="getItemsFB")//降级方法
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        //{1}时RestTemplate定义的一种占位符格式，后面第三个参数orderId会对占位符进行填充
        return rt.getForObject("http://item-service/{1}", JsonResult.class,orderId);
    }

    //调用商品服务，减少商品库存
    @PostMapping("/item-service/decreaseNumber")
    @HystrixCommand(fallbackMethod="decreaseNumberFB")
    public JsonResult decreaseNumber(@RequestBody List<Item> items) {
        //发送 post 请求
        //items 是请求协议的具体数据
        return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    @HystrixCommand(fallbackMethod="getUserFB")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }

    @GetMapping("/user-service/{userId}/score")
    @HystrixCommand(fallbackMethod="addScoreFB")
    public JsonResult addScore(
            @PathVariable Integer userId, Integer score) {
        return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    @HystrixCommand(fallbackMethod="getOrderFB")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
    }

    @GetMapping("/order-service")
    @HystrixCommand(fallbackMethod="addOrderFB")
    public JsonResult addOrder() {
        return rt.getForObject("http://order-service/", JsonResult.class);
    }

    //降级方法的参数和返回值，需要和原始方法一致，方法名任意
    public JsonResult<List<Item>> getItemsFB(String orderId) {
        return JsonResult.err("获取订单商品列表失败");
    }
    public JsonResult decreaseNumberFB(List<Item> items) {
        return JsonResult.err("更新商品库存失败");
    }
    public JsonResult<User> getUserFB(Integer userId) {
        return JsonResult.err("获取用户信息失败");
    }
    public JsonResult addScoreFB(Integer userId, Integer score) {
        return JsonResult.err("增加用户积分失败");
    }
    public JsonResult<Order> getOrderFB(String orderId) {
        return JsonResult.err("获取订单失败");
    }
    public JsonResult addOrderFB() {
        return JsonResult.err("添加订单失败");
    }
}
