package com.demo.ums.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import org.springframework.http.HttpStatus;

import java.util.Collection;

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

    /**
     * 创建一个返回实例
     */
    public static JsonResult getSuccessInstance() {
        return getInstance(HttpStatus.OK, "success");
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getSuccessInstance(String message) {
        return getInstance(HttpStatus.OK, message);
    }

    /**
     * 创建一个返回实例
     */
    public static JsonResult getInstance(HttpStatus httpStatus) {
        return getInstance(httpStatus, httpStatus.getReasonPhrase());
    }

    /**
     * 创建一个返回实例,自定义返回信息
     */
    public static JsonResult getInstance(HttpStatus httpStatus, String message) {
        int code = httpStatus == HttpStatus.OK ? 0 : httpStatus.value();
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setData(NULL_OBJECT);
        return jsonResult;
    }

    /**
     * 设置数据和总量
     */
    @Deprecated
    public void setDataAndTotal(Object object) {
        data = object;
        if (object != null) {
            if (object instanceof Page) {
                Page page = (Page) object;
                total = page.getTotal();
            } else if (object instanceof Collection) {
                total = (long) ((Collection) object).size();
            }
        }
    }

    public String toJson() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
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
