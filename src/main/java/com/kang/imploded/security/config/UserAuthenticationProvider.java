package com.kang.imploded.security.config;

import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.service.SecurityUserDetailsService;
import com.kang.sys.entity.Role;
import com.kang.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录验证
 * @Author Sans
 * @CreateTime 2019/10/1 19:11
 */
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserDetailsService userDetailsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisOperator redisOperator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SecurityUser securityUser = userDetailsService.loadUserByUsername(userName);
        if (securityUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, securityUser.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (securityUser.getStatus().equals("PROHIBIT")){
            throw new LockedException("该用户已被冻结");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<Role> roleList = userService.selectRoleByUserId(securityUser.getUserId());
        for (Role role: roleList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
        }
        securityUser.setAuthorities(authorities);
        log.info("UserAuthenticationProvider : authenticate securityUser ={}",securityUser);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(securityUser, password, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
