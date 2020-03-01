package com.kang.sys.service;

import com.kang.sys.dto.UserRoleDto;
import com.kang.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
public interface IRoleService extends IService<Role> {

    /**
     * 添加新角色
     * @param userRoleDto
     */
    void addRole(UserRoleDto userRoleDto);

    /**
     * 查找当前租户默认角色id
     * @return
     */
    Long findDefault();

    /**
     * 删除用户角色
     * @param roleId
     */
    void delRole(Long roleId);
}
