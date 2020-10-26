package cn.tedu.sp02.item.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems(String orderId) {
        ArrayList<Item> items=new ArrayList<>();
        items.add(new Item(1,"电视机1",3));
        items.add(new Item(2,"电视机2",3));
        items.add(new Item(3,"电视机3",3));
        items.add(new Item(4,"电视机4",3));
        items.add(new Item(5,"电视机5",3));
        return items;
    }

    @Override
    public void decreaseNumbers(List<Item> itemList) {
        if(log.isInfoEnabled()){
            for (Item item:itemList){
                log.info("减少商品库存:"+item);
            }
        }
    }
}
