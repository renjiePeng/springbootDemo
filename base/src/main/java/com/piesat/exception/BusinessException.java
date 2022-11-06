package com.piesat.exception;

import com.piesat.model.ResponseCode;

/**
 * @Author prj
 * 基础的业务异常信息基类
 */
public class BusinessException extends RuntimeException{
    private ResponseCode responseCode;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(ResponseCode responseCode) {
        this.responseCode =responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BusinessException(ResponseCode responseCode, Throwable cause) {
        //super(cause);
        this.responseCode =responseCode;
    }
}

