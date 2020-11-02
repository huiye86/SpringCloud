package cn.tedu.sp03.service;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RefreshScope //将更新的配置数据注入到Bean
public class UserServiceImpl implements UserService {

    @Value("${sp.user-service.users}")
    private String userJson;

    @Override
    public User getUser(Integer userId) {
        log.info("user json string:"+userJson);
        List<User> userList = JsonUtil.from(userJson, new TypeReference<List<User>>() {
        });
        for (User user:userList){
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return new User(userId,"name-"+userId,"pwd-"+userId);
    }

    @Override
    public void addScore(Integer userId, Integer score) {
        log.info("增加用户积分.userId="+userId+",score="+score);
    }
}
