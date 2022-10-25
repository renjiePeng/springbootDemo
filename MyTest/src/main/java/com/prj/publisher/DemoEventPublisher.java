package com.prj.publisher;

import com.prj.event.OrderEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @className: cpm.prj.publisher.DemoEventPublisher
 * @description:
 * @author: pengrj
 * @create: 2022-10-12 10:08
 */
@Component
public class DemoEventPublisher {

    @Resource
    private ApplicationContext applicationContext;

    public void publisher(Object o){
        applicationContext.publishEvent(new OrderEvent(this,o));
    }
}
