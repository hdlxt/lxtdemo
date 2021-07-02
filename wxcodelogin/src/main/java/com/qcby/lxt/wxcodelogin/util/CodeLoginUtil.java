package com.qcby.lxt.wxcodelogin.util;

import java.io.*;
import java.util.Random;

/**
 * @ClassName CodeLoginUtil
 * @Description 扫码登录工具类
 * @Author lxt
 * @Date 2021/7/1 22:29
 */
public class CodeLoginUtil {
    /**
     * 从字节数组到十六进制字符串转换
     */
    public static String bytes2HexString(byte[] b) {
        byte[] buff = new byte[2 * b.length];
        for (int i = 0; i < b.length; i++) {
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[b[i] & 0x0f];
        }
        return new String(buff);
    }


    private final static byte[] hex = "0123456789ABCDEF".getBytes();

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
