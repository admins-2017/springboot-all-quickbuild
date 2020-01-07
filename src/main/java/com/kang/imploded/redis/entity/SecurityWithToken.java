package com.kang.imploded.redis.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author kang
 */
@Data
public class SecurityWithToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;


    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 用户token
     */
    private String token;

}
