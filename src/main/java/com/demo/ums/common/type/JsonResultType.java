package com.demo.ums.common.type;

/**
 * Created on 2017/8/1.
 *
 * @author vikde
 */
public enum JsonResultType {
    /**
     * 请求成功
     */
    SYSTEM_SUCCESS(0, "请求成功"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(1, "系统异常"),
    /**
     * 接口不存在
     */
    SYSTEM_API_NOT_FOUND(2, "接口不存在"),
    /**
     * 参数异常
     */
    SYSTEM_API_PARAMETER_EXCEPTION(3, "参数异常"),
    /**
     * 用户相关异常
     */
    USER_EXCEPTION(1100, ""),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(1101, "用户未登录"),
    /**
     * 用户名或密码错误
     */
    USER_NAME_OR_PASSWORD_ERROR(1102, "用户名或密码错误"),
    /**
     * 用户未激活
     */
    USER_NOT_ACTIVE(1103, "用户未激活"),
    /**
     * 未知的用户状态
     */
    USER_STATUS_UNKNOWN(1104, "未知的用户状态"),
    /**
     * 用户已被删除
     */
    USER_IS_DELETED(1105, "用户已被删除"),
    /**
     * 两次输入的密码不一致密码
     */
    USER_PASSWORD_DIFFERENT(1106, "两次输入的密码不一致密码"),
    /**
     * 用户已存在
     */
    USER_ALREADY_EXIST(1107, "用户已存在"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(1108, "用户不存在"),
    /**
     * 权限组还在使用
     */
    PERMISSION_GROUP_USED(1201, "权限组还在使用"),
    /**
     * 权限还在使用
     */
    PERMISSION_USED(1301, "权限还在使用"),
    /**
     * 角色还在使用
     */
    ROLE_USED(1401, "角色还在使用");

    private int code;
    private String defaultMessage;

    JsonResultType(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
