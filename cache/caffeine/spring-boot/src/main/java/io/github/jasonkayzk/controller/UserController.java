package io.github.jasonkayzk.controller;


import io.github.jasonkayzk.cache.UserCache;
import io.github.jasonkayzk.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserCache userCache;

    @GetMapping("/{username}/{userId}")
    public String getUnit(@PathVariable String username,
                        @PathVariable String userId) {
        return userCache.getUnit(username, userId).toString();
    }

    @DeleteMapping("/{username}/{userId}")
    public void deleteUnit(@PathVariable String username,
                                  @PathVariable String userId) {
        userCache.deleteUnit(username, userId);
    }

    @GetMapping("/cachestat")
    public String cacheStat() {
        // 获取Cache实例，并调用stat()方法查看缓存情况
        return userCache.getCache().stats().toString();
    }

}
