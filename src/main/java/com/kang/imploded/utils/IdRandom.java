package com.kang.imploded.utils;

import java.util.Random;

/**
 * 为id随机生成字符串 暂时写成这样
 * @author kang
 * @version 1.0
 * @date 2020/1/8 17:21
 */
public class IdRandom {

    public static String getRandom(){
        Random ran=new Random();
        int a=ran.nextInt(99999999);
        int b=ran.nextInt(99999999);
        long l=a*10000000L+b;
        String num=String.valueOf(l);
        return num;
    }


}
