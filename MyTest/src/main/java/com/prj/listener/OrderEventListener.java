package com.prj.listener;

import com.prj.event.OrderEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @className: cpm.prj.event.com.prj.listener.OrderEventListener
 * @description: 自定义事件监听器
 * @author: pengrj
 * @create: 2022-10-12 10:04
 */
@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {

    @Async
    @Override
    public void onApplicationEvent(OrderEvent event) {
        try {
            System.out.println("开始做事："+Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String s = event.getObject().toString();
        System.out.println("结束做事："+s);
    }
}
