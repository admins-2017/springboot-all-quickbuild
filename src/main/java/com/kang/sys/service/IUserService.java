package com.kang.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.UpdateUserDto;
import com.kang.sys.dto.UserWithDetails;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.MenuTreeVo;
import com.kang.sys.vo.db.UserWithDetailsVo;

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

    /**
     * 根据用户id查询功能列表
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<MenuTreeVo> selectMenuTreeByUserId(Long userId);


    /**
     * 根据角色id查询权限
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<MenuTreeVo> selectMenuTreeByRoleId(Long userId);

    /**
     * 获取当前租户下所有用户信息
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    Page<UserWithDetailsVo> queryUserWithDetails(Page<UserWithDetailsVo> page);

    /**
     * 添加用户及用户详情
     * @CreateTime 2019/9/18 18:01
     * @Param details 用户实体
     */
    void addUserAndDetails(UserWithDetails details);

    /**
     * 删除用户及用户详情
     * @param userId
     */
    void deleteUserAndDetails(Long userId);

    /**
     * 根据用户名，邮箱，电话号进行模糊查询
     * @param likeName
     * @return
     */
    List<UserWithDetailsVo> findUserWithLikeName(String likeName);

    /**
     * 用于修改用户名及用户详情
     * @param updateUserDto
     */
    void updateUserWithDetails(UpdateUserDto updateUserDto);
}
