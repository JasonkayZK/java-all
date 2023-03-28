package io.github.jasonkayzk.controller;


import io.github.jasonkayzk.cache.UserCache;
import io.github.jasonkayzk.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserCache userCache;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{userType}/{userId}")
    public User getUser(@PathVariable String userType,
                        @PathVariable String userId) {
        logger.info("getUser: userType: {}, userId: {}", userType, userId);

        userCache.getCache().asMap().forEach((key, user) -> logger.info(user.toString()));
        return userCache.getUser(userType, userId);
    }

    @DeleteMapping("/{userType}/{userId}")
    public void deleteUser(@PathVariable String userType,
                                  @PathVariable String userId) {
        userCache.deleteUser(userType, userId);
    }

    @GetMapping("/cachestat")
    public String cacheStat() {
        // 获取Cache实例，并调用stat()方法查看缓存情况
        return userCache.getCache().stats().toString();
    }

}
