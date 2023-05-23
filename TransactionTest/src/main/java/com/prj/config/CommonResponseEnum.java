package com.prj.config;

/**
 * @Author prj
 */
public enum CommonResponseEnum implements ResponseCode {
    /**
     * 操作成功
     */
    SUCCESS(200,"操作成功"),

    /**
     * 操作失败
     */
    ERROR(100, "操作失败"),

    /**
     * 服务器内部错误
     */
    SYS_ERROR(101, "服务器内部错误"),

    /**
     * 对象不能为空
     */
    OBJECT_NOT_NULL(102, "对象不能为空"),

    /**
     * 对象为空
     */
    OBJECT_NULL(103, "对象为空"),

    /**
     * 对象为真
     */
    OBJECT_TRUE(104, "对象为真"),

    /**
     * 两个对象不相等
     */
    OBJECT_NOT_EQUALS(105, "两个对象不相等"),

    /**
     * 两个对象相等
     */
    OBJECT_EQUALS(105, "两个对象相等"),

    /**
     * 字符串为空
     */
    OBJECT_NOT_EMPTY(106, "字符串为空"),
    ;

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应信息
     */
    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CommonResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponseEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
