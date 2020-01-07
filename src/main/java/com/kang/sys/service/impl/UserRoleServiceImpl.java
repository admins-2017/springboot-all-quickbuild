package com.kang.sys.service.impl;

import com.kang.sys.entity.UserRole;
import com.kang.sys.mapper.UserRoleMapper;
import com.kang.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
