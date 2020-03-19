package com.kang.sys.service;

import com.kang.sys.entity.UserDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.LoginSuccessVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface IUserDetailsService extends IService<UserDetails> {

    /**
     * 根据id查询用户详情
     * @param userId
     * @return
     */
    LoginSuccessVo getUserDetailsById(Long userId);
}
