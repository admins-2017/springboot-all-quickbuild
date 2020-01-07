package com.kang.imploded.security.until;

import com.kang.imploded.security.entity.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 * @Author kang
 * @CreateTime 2019/11/06 13:16
 */
public class SecurityUntil {

    /**
     * 私有化构造器
     */
    private SecurityUntil(){}

    /**
     * 获取当前用户信息
     */
    public static SecurityUser getUserInfo(){
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userDetails;
    }
    /**
     * 获取当前用户ID
     */
    public static Long getUserId(){
        return getUserInfo().getUserId();
    }
    /**
     * 获取当前用户账号
     */
    public static String getUserName(){
        return getUserInfo().getUsername();
    }
}