package com.kang.imploded.security.until;

import com.kang.imploded.security.entity.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

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

    /**
     * 获取当前用户角色
     */
    public static List<GrantedAuthority> getRole(){
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities;
    }

    /**
     * 获取当前租户信息
     * @return
     */
    public static Long getTenantId(){
        return getUserInfo().getTenantId();
    }

}