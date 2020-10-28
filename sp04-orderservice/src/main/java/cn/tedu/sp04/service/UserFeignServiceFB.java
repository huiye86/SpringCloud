package cn.tedu.sp04.service;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

/**
 * @author wxh
 * @date 2020/10/28 14:23
 * @description
 */
@Component
public class UserFeignServiceFB implements UserFeignService{
    @Override
    public JsonResult<User> getUser(Integer userId) {
        if(Math.random()<0.4) {
            return JsonResult.ok(new User(userId, "缓存name"+userId, "缓存pwd"+userId));
        }
        return JsonResult.err("无法获取用户信息");
    }

    @Override
    public JsonResult addScore(Integer userId, Integer score) {
        return JsonResult.err("无法增加用户积分");
    }
}