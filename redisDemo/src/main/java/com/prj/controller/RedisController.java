package com.prj.controller;

import com.prj.config.RedisConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: com.prj.config.controller.RedisController
 * @description:
 * @author: pengrj
 * @create: 2022-10-10 13:42
 */
@RefreshScope
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisConfig redisConfig;

    @Value(value = "${str}")
    private String str;

    @GetMapping("/get")
    public String get(){
        //RedisTemplate<String, String> redisTemplate = redisConfig.getRedisTemplate(1);
        System.out.println(str);
        return str;//redisTemplate.opsForValue().get("HT1-A:TMC001");
    }
}
