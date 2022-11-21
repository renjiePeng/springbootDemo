package com.prj.controller;

import com.prj.pojo.entity.Student;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.prj.controller.StudentController
 * @description:
 * @author: pengrj
 * @create: 2022-11-15 15:33
 */
@RestController
@Slf4j
@Api(tags = "学生控制器类")
public class StudentController {

    @PostMapping("/insert")
    public String insert(@RequestBody Student student){
        log.info("student:{}",student.toString());
        return student.toString();
    }
}
