package com.demo.ums.common.type;

/**
 * Created on 2017/7/26.
 * 用户状态类型
 *
 * @author vikde
 */
public enum UserStatusType {
    /**
     * 未激活
     */
    INACTIVE(1),
    /**
     * 正常
     */
    ACTIVE(2),
    /**
     * 已删除
     */
    DELETED(3);

    UserStatusType(int index) {
        this.index = index;
    }

    private final int index;

    public int getIndex() {
        return index;
    }
}
