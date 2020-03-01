package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.ParseMenuTreeUtil;
import com.kang.sys.dto.AssigningRolesDto;
import com.kang.sys.dto.UpdateOrDeleteRoleDto;
import com.kang.sys.dto.UserRoleDto;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.RoleMenu;
import com.kang.sys.entity.UserRole;
import com.kang.sys.service.IRoleService;
import com.kang.sys.service.IUserRoleService;
import com.kang.sys.service.IUserService;
import com.kang.sys.vo.MenuTreeVo;
import com.kang.sys.vo.RoleMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/role")
@Api("角色详情")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "新增角色",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    @SysLog(description ="添加新角色")
    public JSONResult addRole(@RequestBody UserRoleDto userRoleDto){
        roleService.addRole(userRoleDto);
        return JSONResult.ok();
    }


//    @ApiOperation(value = "获取当前租户下所有角色 分页",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
//    @GetMapping("/{page}/{size}")
//    public JSONResult queryRoleWithTenant(@PathVariable Integer page,@PathVariable Integer size){
//        Page<Role> pages = new Page<>(page, size);
//        IPage<Role> roleIPage = roleService.page(pages, new QueryWrapper<Role>()
//                .eq("tenant_id", SecurityUntil.getTenantId()));
//        System.out.println(roleIPage.getRecords());
//        return JSONResult.ok(roleIPage);
//    }

    @ApiOperation(value = "获取当前租户下所有角色 分页",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
    @GetMapping("/{page}/{size}")
    @SysLog(description ="查询所有角色")
    public JSONResult queryRoleWithTenant(@PathVariable Integer page,@PathVariable Integer size){
        Page<RoleMenuVo> pages = new Page<>(page, size);
        List<Role> list = roleService.list(new QueryWrapper<Role>().
                select("role_id", "role_name", "role_code", "role_description")
                .eq("tenant_id", SecurityUntil.getTenantId()).eq("del_flag", 0));
        List<RoleMenuVo> roleMenuVoList= new ArrayList<>();
        for (Role role: list) {
            RoleMenuVo roleMenuVo = new RoleMenuVo();
            BeanUtils.copyProperties(role,roleMenuVo);

            List<MenuTreeVo> menus = userService.selectMenuTreeByRoleId(role.getRoleId());
            List<MenuTreeVo> menuList = new ArrayList<>();
            if (menus.get(0) != null) {
                menuList = ParseMenuTreeUtil.parseMenuTree(menus);
            }

            roleMenuVo.setChildren(menuList);
            roleMenuVoList.add(roleMenuVo);

        }
        Page<RoleMenuVo> roleMenuVoPage = pages.setRecords(roleMenuVoList);
        return JSONResult.ok(roleMenuVoPage);
    }

    @ApiOperation(value = "获取当前租户下所有角色 不分页",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
    @GetMapping("/")
    @SysLog(description ="查询所有角色")
    public JSONResult queryRoleAll(){
        List<Role> roleList = roleService.list(new QueryWrapper<Role>().eq("tenant_id", SecurityUntil.getTenantId()).eq("del_flag", 0));
        return JSONResult.ok(roleList);
    }

    @ApiOperation(value = "根据角色id进行修改",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PutMapping("/")
    @SysLog(description ="修改角色信息")
    public JSONResult updateRole(UpdateOrDeleteRoleDto roleDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        roleService.update(role,new UpdateWrapper<Role>().eq("role_id",roleDto.getRoleId()));
        return JSONResult.ok();
    }

    @ApiOperation(value = "根据角色状态进行修改 ",notes = "添加角色，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PutMapping("/status")
    @SysLog(description ="启停角色")
    public JSONResult updateRoleStatus(UpdateOrDeleteRoleDto roleDto){
        roleService.update(new UpdateWrapper<Role>().set("del_flag",roleDto.getRoleDelFlag()).eq("role_id",roleDto.getRoleId()));
        return JSONResult.ok();
    }

    /**
     * 预留接口 在删除角色时将用户对应的角色改为默认角色 需要添加默认角色字段
     * @return
     */
    @DeleteMapping("/{roleId}")
    @SysLog(description ="删除角色")
    public JSONResult delRoleAndUpdateUserRole(@PathVariable Long roleId){
        roleService.delRole(roleId);
        return JSONResult.ok("删除角色成功");
    }

    /**
     *
     * @param userRole
     * @return
     */
    @ApiOperation(value = "用于给用户添加角色 ")
    @PostMapping("/AssigningRoles")
    @SysLog(description ="为用户添加角色信息")
    public JSONResult addRoleWithUser(@RequestBody UserRole userRole){
        boolean save = userRoleService.save(userRole);
        return JSONResult.ok(save);
    }


}
