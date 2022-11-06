package com.piesat.exception;

import com.piesat.model.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.piesat.exception.GlobalExceptionHandler
 * @description:
 * @author: pengrj
 * @create: 2022-11-04 16:06
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Throwable.class)
//    public R handleException(Throwable t, HttpServletResponse response){
//        return R.error(t.getMessage());
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public R handleException(BusinessException e){
        e.printStackTrace();
        return R.error(e.getMsg(),e.getResponseCode().getCode());
    }
}
