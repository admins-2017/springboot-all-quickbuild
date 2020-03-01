package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.UpdateUserDto;
import com.kang.sys.dto.UserWithDetails;
import com.kang.sys.entity.*;
import com.kang.sys.mapper.RoleMapper;
import com.kang.sys.mapper.UserDetailsMapper;
import com.kang.sys.mapper.UserMapper;
import com.kang.sys.mapper.UserRoleMapper;
import com.kang.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.MenuTreeVo;
import com.kang.sys.vo.db.UserWithDetailsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户名查询实体
     * @Author Sans
     * @CreateTime 2019/9/14 16:30
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    @Override
    public User selectUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return this.baseMapper.selectRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @Override
    public List<Menu> selectMenuByUserId(Long userId) {
        return this.baseMapper.selectMenuByUserId(userId);
    }

    @Override
    public List<MenuTreeVo> selectMenuTreeByUserId(Long userId) {
        return this.baseMapper.selectMenuTreeByUserId(userId);
    }

    @Override
    public List<MenuTreeVo> selectMenuTreeByRoleId(Long userId) {
        return this.baseMapper.selectMenuTreeByRoleId(userId);
    }

    @Override
    public Page<UserWithDetailsVo> queryUserWithDetails(Page<UserWithDetailsVo> page) {
        return page.setRecords(this.baseMapper.queryUserWithDetails(page,SecurityUntil.getTenantId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserAndDetails(UserWithDetails userWithDetails) {
            Long userId=Long.parseLong(IdRandom.getRandom());

            //先保存user信息
            User user = new User();
            user.setUserId(userId);
            user.setUsername(userWithDetails.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(userWithDetails.getPassword()));
            user.setTenantId(SecurityUntil.getTenantId());

            this.baseMapper.insert(user);

            //添加用户详情信息
            UserDetails userDetails = new UserDetails();
            userDetails.setUserDetailsUrl(userWithDetails.getUserDetailsUrl());
            userDetails.setUserDetailsAddr(userWithDetails.getUserDetailsAddr());
            userDetails.setUserDetailsMail(userWithDetails.getUserDetailsMail());
            userDetails.setUserDetailsSex(userWithDetails.getUserDetailsSex());
            userDetails.setUserDetailsTel(userWithDetails.getUserDetailsTel());
            userDetails.setShopId(1);
            userDetails.setUserId(userId);

            userDetailsMapper.insert(userDetails);

            //赋默认角色
            if(0 == userWithDetails.getRoleId()){
                //查询当前租户下默认角色
                Role role = roleMapper.selectOne(new QueryWrapper<Role>().select("role_id")
                        .eq("tenant_id", SecurityUntil.getTenantId()).eq("default_role",1));
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getRoleId());
                userRoleMapper.insert(userRole);
            }else {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(userWithDetails.getRoleId());
                userRoleMapper.insert(userRole);
            }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserAndDetails(Long userId) {
        this.baseMapper.deleteById(userId);
        userRoleMapper.delete(new UpdateWrapper<UserRole>().eq("user_id",userId));
        userDetailsMapper.delete(new UpdateWrapper<UserDetails>().eq("user_id",userId));
    }

    @Override
    public List<UserWithDetailsVo> findUserWithLikeName(String likeName) {
        String newLikeName = "%"+likeName+"%";
        return this.baseMapper.findUserWithLikeName(SecurityUntil.getTenantId(),newLikeName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserWithDetails(UpdateUserDto updateUserDto) {
        this.baseMapper.update(null,new UpdateWrapper<User>().set("username",updateUserDto.getUsername())
                .eq("user_id",updateUserDto.getUserId()));
        userDetailsMapper.update(null,new UpdateWrapper<UserDetails>()
                .set("user_details_url",updateUserDto.getUserDetailsUrl()).set("user_details_sex",updateUserDto.getUserDetailsSex())
                .set("user_details_addr",updateUserDto.getUserDetailsAddr()).set("user_details_mail",updateUserDto.getUserDetailsMail())
                .set("user_details_tel",updateUserDto.getUserDetailsTel()).eq("user_id",updateUserDto.getUserId()));
    }


}
