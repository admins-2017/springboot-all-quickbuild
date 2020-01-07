package com.kang.sys.mapper;

import com.kang.sys.entity.Menu;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<Role> 角色名集合
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<Menu> 角色名集合
     */
    List<Menu> selectMenuByUserId(Long userId);
}
