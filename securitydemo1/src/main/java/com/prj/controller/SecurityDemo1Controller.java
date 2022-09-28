package com.prj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: com.prj.controller
 * @Author 彭仁杰
 * @Date 2022/6/26 20:13
 * @Description
 **/
@RestController
@RequestMapping("/demo1")
public class SecurityDemo1Controller{

    @GetMapping("/test")
    public String test(){
        return "123";
    }
}
