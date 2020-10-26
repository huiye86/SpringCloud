package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 功能：为了测试负载均衡
     * */
    @Value("${server.port}")
    private int port;

    /**
     * 业务功能：查询订单信息
     * url:
     * 返回值：
     * */
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        log.info("获取订单的商品列表，orderId="+orderId);

        //设置随机延迟
        if (Math.random()<0.9){
            long t=new Random().nextInt(5000);
            log.info("item-service-"+port+"暂停"+t);
            Thread.sleep(t);
        }

        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok().msg("port="+port).data(items);
    }

    /**
     * @RequestBody 完整接受请求协议体的内容，转成商品集合
     * */
    @PostMapping("decreaseNumber")
    public JsonResult decreaseNumber(@RequestBody List<Item> items){
        itemService.decreaseNumbers(items);
        return JsonResult.ok().msg("商品库存减少成功");
    }
}
