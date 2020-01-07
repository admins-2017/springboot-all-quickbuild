package com.kang.sys.controller;


import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.sys.entity.Menu;
import com.kang.sys.service.IMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 普通用户
 * @Author Sans
 * @CreateTime 2019/10/2 14:43
 */
@RestController
@RequestMapping("/user")
@Api("用户详情")
public class UserController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 用户端信息
     * @Author Sans
     * @CreateTime 2019/10/2 14:52
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public JSONResult userLogin(HttpServletRequest request){
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();

        Long userId = (Long)request.getSession().getAttribute("userId");
        System.out.println("session获取"+userId);

        String userToken="user:token:"+userId;
        Object s = redisOperator.getObj(userToken);
        System.out.println("对象:"+s);
        return JSONResult.ok(userDetails);
    }

    /**
     * 拥有USER角色和sys:user:info权限可以访问
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('USER') and hasPermission('/user/menuList','sys:user:info')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public JSONResult sysMenuEntity(){
        List<Menu> menus = menuService.list();
        return JSONResult.ok(menus);
    }

}
