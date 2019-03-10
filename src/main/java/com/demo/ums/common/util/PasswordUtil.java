package com.demo.ums.common.util;

import com.demo.ums.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vikde
 * @date 2017/12/12
 */
public class PasswordUtil {
    private final static Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

    /**
     * 将原始密码转换成加密的密码
     */
    public static String encryptPassword(String originalPassword) {
        try {
            return AesUtil.encryptAsHex(Config.PASSWORD, originalPassword);
        } catch (Exception e) {
            logger.error("将原始密码转换成加密的密码异常", e);
        }
        return null;
    }

    /**
     * 将加密后的密码转换成原始的密码
     */
    public static String decryptPassword(String password) {
        try {
            return AesUtil.decryptToString(Config.PASSWORD, password);
        } catch (Exception e) {
            logger.error("将加密后的密码转换成原始的密码异常", e);
        }
        return null;
    }
}
