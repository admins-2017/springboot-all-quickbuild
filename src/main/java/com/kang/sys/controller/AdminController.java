package com.kang.sys.controller;

import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.User;
import com.kang.sys.service.IMenuService;
import com.kang.sys.service.IRoleService;
import com.kang.sys.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端
 * @Author Sans
 * @CreateTime 2019/10/2 14:16
 */
@RestController
@RequestMapping("/admin")
@Api("管理员详情")
public class AdminController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 管理端信息
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public JSONResult userLogin(){
        SecurityUser userDetails = SecurityUntil.getUserInfo();
        return JSONResult.ok(userDetails);
    }

    /**
     * 拥有ADMIN或者USER角色可以访问
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        List<User> userList = userService.list();
        return JSONResult.ok(userList);
    }

    /**
     * 拥有ADMIN和USER角色可以访问
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN') and hasRole('USER')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public JSONResult menuList(){
        List<Menu> menuList = menuService.list();
        return JSONResult.ok(menuList);
    }


    /**
     * 拥有sys:user:info权限可以访问
     * hasPermission 第一个参数是请求路径 第二个参数是权限表达式
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public JSONResult userList(){
        List<User> sysUserEntityList = userService.list();
        return JSONResult.ok(sysUserEntityList);
    }


    /**
     * 拥有ADMIN角色和sys:role:info权限可以访问
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN') and hasPermission('/admin/adminRoleList','sys:role:info')")
    @RequestMapping(value = "/adminRoleList",method = RequestMethod.GET)
    public JSONResult adminRoleList(){
        List<Role> roleList = roleService.list();
        return JSONResult.ok(roleList);
    }


    @RequestMapping("testSecurity")
    public void testSecurityUser(){
        SecurityUser securityUser =SecurityUntil.getUserInfo();
        System.out.println(securityUser.toString());
    }
}
