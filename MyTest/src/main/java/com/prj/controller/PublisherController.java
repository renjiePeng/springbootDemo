package com.prj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.prj.event.OrderEvent;
import com.prj.pojo.entity.User;
import com.prj.publisher.DemoEventPublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className: cpm.prj.controller.PublisherController
 * @description:
 * @author: pengrj
 * @create: 2022-10-12 10:25
 */
@Api(tags = "测试")
@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Resource
    private DemoEventPublisher demoEventPublisher;

    @GetMapping("/publisher1")
    public String publisher1(){
        demoEventPublisher.publisher("ddddd");
        demoEventPublisher.publisher("111");
        demoEventPublisher.publisher("222");
        demoEventPublisher.publisher("333");
        demoEventPublisher.publisher("444");
        demoEventPublisher.publisher("555");
        return "ok";
    }

    @ApiOperation("/获取时间信息")
    @PostMapping("/getDate")
    public Object getDate(@RequestBody User user){
        System.out.println("action method: " + user.getDate().getTime());
        return user;
    }
    
    @ApiOperation("/获取用户信息")
    @GetMapping("/getUser")
    public Object getUser(){
        List<User> users = buildUser();
        System.out.println("controller运行");
        return JSON.toJSONStringWithDateFormat(
                users,"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat
        );
    }

    @NotNull
    private List<User> buildUser(){
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(12);
        user1.setName("张三");
        user1.setDate(new Date());

        User user2 = new User();
        user2.setAge(17);
        user2.setName("李四");
        user2.setDate(new Date());
        users.add(user1);
        users.add(user2);
        return users;
    }
}

