package com.demo.ums.common;

/**
 * Created on 2017/3/24.
 *
 * @author vikde
 */
public class JsonResult {
    public final static Object NULL_OBJECT = new Object();
    private int code;
    private String message = "";
    private Object data;
    /**
     * 可选参数
     */
    private Long total;

    private JsonResult() {
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getSuccessInstance() {
        return getSuccessInstance("success");
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getSuccessInstance(String message) {
        return getInstance(0, message);
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getRequestExceptionInstance(String message) {
        return getInstance(1, message);
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getServiceExceptionInstance(String message) {
        return getInstance(2, message);
    }

    /**
     * 创建一个返回实例,自定义返回信息
     */
    private static JsonResult getInstance(int code, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setData(NULL_OBJECT);
        return jsonResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
