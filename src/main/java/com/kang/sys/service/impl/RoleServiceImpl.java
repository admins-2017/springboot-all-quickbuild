package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.UserRoleDto;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.RoleMenu;
import com.kang.sys.entity.UserRole;
import com.kang.sys.mapper.RoleMapper;
import com.kang.sys.mapper.RoleMenuMapper;
import com.kang.sys.mapper.UserRoleMapper;
import com.kang.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(UserRoleDto userRoleDto) {
        Role role=new Role();
        BeanUtils.copyProperties(userRoleDto,role);
        role.setTenantId(SecurityUntil.getTenantId());
        role.setRoleId(Long.parseLong(IdRandom.getRandom()));
        log.info("role={}",role);
        this.baseMapper.insert(role);
    }

    @Override
    public Long findDefault() {
        Role role = this.baseMapper.selectOne(new QueryWrapper<Role>()
                .eq("default_role", 1).eq("tenant_id", SecurityUntil.getTenantId()));
        return role.getRoleId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRole(Long roleId) {
        Long defaultRoleId = findDefault();
        //将删除的角色id改为当前租户下默认角色id
        userRoleMapper.update(null,
                new UpdateWrapper<UserRole>().set("role_id",defaultRoleId).eq("role_id",roleId));
        //删除角色对应权限
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        //删除角色
        this.baseMapper.deleteById(roleId);
    }
}
