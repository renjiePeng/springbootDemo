package com.prj.event;

import org.springframework.context.ApplicationEvent;

/**
 * @className: cpm.prj.event.OrderEvent
 * @description: 自定义事件
 * @author: pengrj
 * @create: 2022-10-12 10:01
 */
public class OrderEvent extends ApplicationEvent {

    private Object object;

    public OrderEvent(Object source,Object t) {
        super(source);
        this.object = t;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
