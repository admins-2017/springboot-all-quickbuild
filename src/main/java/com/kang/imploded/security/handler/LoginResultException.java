package com.kang.imploded.security.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/15 20:58
 */
public class LoginResultException extends AuthenticationException {

    public LoginResultException(String msg) {
        super(msg);
    }
}
