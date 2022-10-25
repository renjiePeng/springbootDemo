package com.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author prj
 * @ClassName RedisDemoApplication
 * @Description 描述
 * @Date 2022/4/29 13:08
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class RedisDemoApplication {
    public static void main(String[] args){
        System.setProperty("spring.cloud.bootstrap.enabled","true");
        SpringApplication.run(RedisDemoApplication.class,args);
    }
}
