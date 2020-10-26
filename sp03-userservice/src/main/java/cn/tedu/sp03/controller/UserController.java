package cn.tedu.sp03.controller;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/{userId}")
    public JsonResult getUser(@PathVariable Integer userId){
        log.info("get user,userId="+userId);
        User user=userService.getUser(userId);
        return JsonResult.ok(user);
    }

    @RequestMapping("/{userId}/score")
    public JsonResult addScore(@PathVariable Integer userId,Integer score){
        userService.addScore(userId,score);
        return JsonResult.ok();
    }
}
