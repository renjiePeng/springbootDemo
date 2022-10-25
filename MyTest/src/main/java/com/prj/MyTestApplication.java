package com.prj;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: cpm.prj.MyTestApplication
 * @description:
 * 首先，由监听对象注册监听回调函数(Callback)，当事件源触发了事件后，
 * 监听对象会收到事件源的信息，然后决定如何对事件源进行处理，简要流程如下图所示。
 * main方法中的是事件源，orderEvent是事件，OrderEventListener是事件监听器
 * 1、当执行applicationContext.publishEvent(new OrderEvent(applicationContext,"ddddd"));时，等于触发了事件
 * 2、上一步操作会触发orderEvent事件
 * 3、OrderEventListener会收到事件源的数据，决定如何处理该数据
 * 4、浏览器输入：http://localhost:8083/myTest/publisher/publisher1 即可开启
 * @author: pengrj
 * @create: 2022-10-12 10:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyTestApplication {
    public static void main(String[] args) throws NacosException {
        SpringApplication.run(MyTestApplication.class,args);

        ConfigService configService = NacosFactory.createConfigService("192.168.2.76:8848");
// 通过DataId和Group获取配置文件内容
        String contentInfo = configService.getConfig("my-test-test.yaml","my",1000L);
        System.out.println(contentInfo);
    }
}
