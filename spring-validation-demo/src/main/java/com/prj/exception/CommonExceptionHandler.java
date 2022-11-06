package com.prj.exception;

import com.prj.common.Result;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @className: com.prj.exception.CommonExceptionHandler
 * @description:
 * @author: pengrj
 * @create: 2022-11-02 20:08
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return Result.fail("参数校验失败", msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        return Result.fail("参数校验失败", ex.getMessage());
    }

    /**
     * （1）全局数据绑定
     * 应用到所有@RequestMapping注解方法
     * 此处将键值对添加到全局，注解了@RequestMapping的方法都可以获得此键值对
     * @param model model
     */
    @ModelAttribute
    public void addMsg(Model model){
        model.addAttribute("msg","来自CommonExceptionHandler的msg");
    }

    /**
     * 2）全局数据预处理
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * 用来设置WebDataBinder
     * @param binder binder
     */
    @InitBinder()
    public void initBinder(WebDataBinder binder){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class,customDateEditor);
        System.out.println(binder);
    }


    /**
     * （3）全局异常处理
     *  应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行
     *  定义全局异常处理，value属性可以过滤拦截指定异常，此处拦截所有的Exception
     * @param e 异常类
     * @return String
     */
//    @ExceptionHandler(Exception.class)
//    public String handlerException(Exception e){
//        return e.getMessage();
//    }

}
