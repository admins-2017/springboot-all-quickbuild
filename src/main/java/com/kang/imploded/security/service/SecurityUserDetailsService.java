package com.kang.imploded.security.service;

import com.kang.imploded.security.entity.SecurityUser;
import com.kang.sys.entity.User;
import com.kang.sys.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * SpringSecurity用户的业务实现
 * @Author Sans
 * @CreateTime 2019/10/1 17:21
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    /**
     * 查询用户信息
     * @Author Sans
     * @CreateTime 2019/9/13 17:23
     * @Param  username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行方法： loadUserByUsername");
        // 查询用户信息
        User user =userService.selectUserByName(username);
        if (user!=null){
            // 组装参数
            SecurityUser securityUser = new SecurityUser();
            BeanUtils.copyProperties(user,securityUser);
            return securityUser;
        }
        return null;
    }
}