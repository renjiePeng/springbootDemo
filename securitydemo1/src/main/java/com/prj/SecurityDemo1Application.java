package com.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @PackageName: com.prj
 * @Author 彭仁杰
 * @Date 2022/6/26 20:14
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SecurityDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo1Application.class);
    }
}
