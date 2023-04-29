package com.prj.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @PackageName: com.prj.listener
 * @Author 彭仁杰
 * @Date 2022/11/8 22:03
 * @Description
 **/
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 当容器中发布此事件后，方法触发
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件："+event);
    }
}
