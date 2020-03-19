package com.kang.sys.service.impl;

import com.kang.sys.entity.UserDetails;
import com.kang.sys.mapper.UserDetailsMapper;
import com.kang.sys.service.IUserDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.LoginSuccessVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserDetailsMapper, UserDetails> implements IUserDetailsService {

    @Override
    public LoginSuccessVo getUserDetailsById(Long userId) {
        return this.baseMapper.getUserDetailsById(userId);
    }
}
