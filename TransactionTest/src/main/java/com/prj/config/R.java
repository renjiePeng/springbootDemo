package com.prj.config;


import java.io.Serializable;

/**
 * @author pengrj
 */
public class R implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object data;

    private R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private R() {

    }
    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }

    public static R ok(Object data){
        return new R(CommonResponseEnum.SUCCESS.getCode(),CommonResponseEnum.SUCCESS.getMessage(),data);
    }

    public static R ok(){
        return new R(CommonResponseEnum.SUCCESS.getCode(),CommonResponseEnum.SUCCESS.getMessage(),null);
    }

    public static R error(){
        return new R(CommonResponseEnum.ERROR.getCode(),CommonResponseEnum.ERROR.getMessage(),null);
    }


    public static R error(String msg){
        return new R(CommonResponseEnum.ERROR.getCode(),msg,null);
    }

    public static R error(ResultStatus resultStatus){
        return new R(resultStatus.getCode(),resultStatus.getMessage(),null);
    }

    public static R error(ResponseCode responseCode){
        return new R(responseCode.getCode(),responseCode.getMessage(),null);
    }

}
