package com.prj.common;

/**
 * @className: com.prj.common.Result
 * @description:
 * @author: pengrj
 * @create: 2022-11-02 20:09
 */
public class Result {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result fail(String msgCode, String msg){
        return new Result().setMsg(msgCode,msg);
    }

    public static Result ok(){
        Result result = new Result();
        result.setMsg("OK");
        return result;
    }

    private Result setMsg(String msgCode, String msg){
        Result result = new Result();
        result.setMsg(msgCode+"===>"+msg);
        return result;
    }

//    enum BusinessCode{
//        参数校验失败;
//    }
}
