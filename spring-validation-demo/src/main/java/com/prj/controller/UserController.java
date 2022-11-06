package com.prj.controller;

import com.piesat.exception.BusinessException;
import com.piesat.model.CommonResponseEnum;
import com.piesat.model.ResponseCode;
import com.prj.annotation.validator.Add;
import com.prj.annotation.validator.Update;
import com.prj.annotation.validator.ValidationList;
import com.prj.common.Result;
import com.prj.pojo.dto.UserDTO;
import com.prj.pojo.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * @className: com.prj.controller.UserController
 * @description:
 * @author: pengrj
 * @create: 2022-11-02 19:49
 */
@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    @ApiOperation("新增用户")
    @PostMapping(value = "/insertUser")
    public String insertUser(@RequestBody @Validated(Add.class) UserDTO user) {
        System.out.println(user.toString());
        return user.toString();
    }

    @ApiOperation("批量新增用户")
    @PostMapping(value = "/insertBatchUser")
    public String insertBatchUser(@RequestBody @Validated(Add.class) ValidationList<UserDTO> users, Model model) {
        System.out.println(users.toString());
        return users.toString();
    }

    @ApiOperation("获取用户")
    @GetMapping("/getUser/{name}")
    public String getUser(@PathVariable @Min(value = 3) String name) {
        System.out.println(name);
        try {
            int num = 2/0;
        } catch (Exception e) {
            ResponseCode responseCode = CommonResponseEnum.valueOf("0不能作为被除数");
            throw new BusinessException(responseCode,e);
        }
        return name;
    }

    @ApiOperation("更新用户")
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody @Validated(Update.class) UserDTO userDTO) {
        System.out.println(userDTO.toString());
        return userDTO.toString();
    }

    @Autowired
    private javax.validation.Validator globalValidator;

    // 编程式校验
    @PostMapping("/saveWithCodingValidate")
    public Object saveWithCodingValidate(@RequestBody UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> validate = globalValidator.validate(userDTO, Add.class);
        // 如果校验通过，validate为空；否则，validate包含未校验通过项
        if (validate.isEmpty()) {
            // 校验通过，才会执行业务逻辑处理
        } else {
            for (ConstraintViolation<UserDTO> userDTOConstraintViolation : validate) {
                // 校验失败，做其它逻辑,
                System.out.println(userDTOConstraintViolation);
            }
        }
        return Result.ok();
    }
}
