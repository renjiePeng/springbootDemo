package com.prj.controller;

import com.prj.event.OrderEvent;
import com.prj.publisher.DemoEventPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className: cpm.prj.controller.PublisherController
 * @description:
 * @author: pengrj
 * @create: 2022-10-12 10:25
 */
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

    @GetMapping("/publisher2")
    public String publisher2(){
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList();

        return "ok";
    }
}

