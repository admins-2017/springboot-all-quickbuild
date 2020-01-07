package com.kang.sys.service;

import com.kang.sys.entity.Menu;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询实体
     * @Author kang
     * @CreateTime 2019/9/14 16:30
     * @Param  username 用户名
     * @Return User 用户实体
     */
    User selectUserByName(String username);
    /**
     * 根据用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<Role> selectRoleByUserId(Long userId);
    /**
     * 根据用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<Menu> selectMenuByUserId(Long userId);
}
