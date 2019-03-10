package com.demo.ums.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author vikde
 * @date 2017/12/05
 */
public class AesUtil {
    /**
     * AES加密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static byte[] encrypt(String key, byte[] bytes) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
        return cipher.doFinal(bytes);
    }

    /**
     * AES加密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static String encryptAsHex(String key, byte[] bytes) throws Exception {
        return byteToHex(encrypt(key, bytes));
    }

    /**
     * AES加密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static String encryptAsHex(String key, String content) throws Exception {
        return byteToHex(encrypt(key, content.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * AES解密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static byte[] decrypt(String key, byte[] bytes) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
        return cipher.doFinal(bytes);
    }

    /**
     * AES解密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static String decryptToString(String key, byte[] bytes) throws Exception {
        return new String(decrypt(key, bytes), StandardCharsets.UTF_8);
    }

    /**
     * AES解密，模式 AES/CBC/PKCS5Padding,使用16位key为密码，16字节空向量
     */
    public static String decryptToString(String key, String bytes) throws Exception {
        return new String(decrypt(key, hexToBytes(bytes)), StandardCharsets.UTF_8);
    }

    /**
     * 将字节转成16进制
     */
    private static String byteToHex(byte[] bytes) {
        String result;
        StringBuilder builder = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xFF);
            if (temp.length() < 2) {
                builder.append(0);
            }
            builder.append(temp);
        }
        result = builder.toString();
        return result;
    }

    /**
     * 将16进制转成字节流
     */
    private static byte[] hexToBytes(String string) {
        int length = string.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = Integer.valueOf(string.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return bytes;
    }

}