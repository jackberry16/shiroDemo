package com.chengh.shirodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("test")
    public Boolean putRedis() {
        redisTemplate.opsForValue().set("test", "aaaaaa");
        return true;
    }

    @PostMapping("test1")
    public Boolean putRedis1() {
        redisTemplate.opsForHash().put("a","b","c");
        return true;
    }

    @GetMapping("test")
    public String getRedis() {
        return (String) redisTemplate.opsForValue().get("test");
    }

    @GetMapping("session")
    public String getSession() {
        Set<Object> a = redisTemplate.opsForHash().keys("test:*");
        return "a" ;
    }

}
