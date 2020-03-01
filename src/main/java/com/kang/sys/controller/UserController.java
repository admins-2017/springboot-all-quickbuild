package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.ParseMenuTreeUtil;
import com.kang.sys.dto.UpdateOrDeleteUserDto;
import com.kang.sys.dto.UpdateUserDto;
import com.kang.sys.dto.UserWithDetails;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.User;
import com.kang.sys.service.IMenuService;
import com.kang.sys.service.IUserDetailsService;
import com.kang.sys.service.IUserService;
import com.kang.sys.vo.MenuTreeVo;
import com.kang.sys.vo.db.UserWithDetailsVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private IUserService userService;
    @Autowired
    private IUserDetailsService userDetailsService;
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
    @SysLog(description ="为用户添加角色信息")
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

    /**
     * 获取当前租户下所有用户
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    @SysLog(description ="查询所有用户信息")
    public JSONResult queryUser(@PathVariable Integer page,@PathVariable Integer size){
        Page<UserWithDetailsVo> pages = new Page<>(page,size);
        Page<UserWithDetailsVo> userWithDetailsVos = userService.queryUserWithDetails(pages);
        return JSONResult.ok(userWithDetailsVos);
    }

    /**
     * 添加用户及用户详情
     * @param details
     * @return
     */
    @PostMapping("/")
    @SysLog(description ="添加用户信息")
    public JSONResult addUser(@RequestBody UserWithDetails details){
        userService.addUserAndDetails(details);
        return JSONResult.ok("新增成功");
    }

    /**
     * 修改用户基本信息
     * @return
     */
    @PutMapping("/")
    @SysLog(description ="修改用户信息")
    public JSONResult updateUser(@RequestBody UpdateUserDto updateUserDto){

         userService.updateUserWithDetails(updateUserDto);
        return JSONResult.ok();
    }

    @DeleteMapping("/{userId}")
    @SysLog(description ="删除用户信息")
    public JSONResult removeUser(@PathVariable String userId){
        userService.deleteUserAndDetails(Long.parseLong(userId));
        return JSONResult.ok();
    }

    @PutMapping("/status")
    @SysLog(description ="启停用户")
    public JSONResult updateUserStatus(UpdateOrDeleteUserDto userStatusVo){
        userService.update(new UpdateWrapper<User>().set("status",userStatusVo.getStatus())
                .eq("user_id",userStatusVo.getUserId()));
        return JSONResult.ok();
    }

    /**
     * 根据前端传递值进行匹配查询
     */
    @GetMapping("/{likeName}")
    @SysLog(description ="根据条件查询用户信息")
    public JSONResult getUserWithLike(@PathVariable String likeName){
        List<UserWithDetailsVo> userList = userService.findUserWithLikeName(likeName);
        return JSONResult.ok(userList);
    }



}
