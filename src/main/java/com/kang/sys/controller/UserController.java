package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.dto.UpdateOrDeleteUserDto;
import com.kang.sys.dto.UpdateUserDto;
import com.kang.sys.dto.UserWithDetails;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.User;
import com.kang.sys.service.IMenuService;
import com.kang.sys.service.IUserService;
import com.kang.sys.vo.db.UserWithDetailsVo;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 普通用户
 * @Author Kdw
 * @CreateTime 2019/10/2 14:43
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户controller",tags = "用户对应操作")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisOperator redisOperator;

    private String keyName = "user:";

    /**
     * 用户端信息
     * @Author Sans
     * @CreateTime 2019/10/2 14:52
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @SysLog(description ="为用户添加角色信息")
    public JSONResult userLogin(HttpServletRequest request){
        removeCache();
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return JSONResult.ok(userDetails);
    }

    /**
     * 拥有USER角色和sys:user:info权限可以访问
     * @Author Sans
     * @CreateTime 2019/10/2 14:22
     * @Return Map<String,Object> 返回数据MAP
     */
//    @PreAuthorize("hasRole('USER') and hasPermission('/user/menuList','sys:user:info')")
//    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
//    public JSONResult sysMenuEntity(){
//        List<Menu> menus = menuService.list();
//        return JSONResult.ok(menus);
//    }

    /**
     * 获取当前租户下所有用户
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{page}/{size}")
    @SysLog(description ="查询所有用户信息")
    public JSONResult queryUser(@PathVariable Integer page,@PathVariable Integer size){
        String key = keyName+ SecurityUntil.getTenantId()+':'+page+':'+size;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }else {
            Page<UserWithDetailsVo> pages = new Page<>(page, size);
            Page<UserWithDetailsVo> userWithDetailsVos = userService.queryUserWithDetails(pages);
            redisOperator.setObj(key,userWithDetailsVos,24);
            return JSONResult.ok(userWithDetailsVos);
        }
    }

    /**
     * 添加用户及用户详情
     * @param details 用户详情
     * @return JSONResult
     */
    @PostMapping("/")
    @SysLog(description ="添加用户信息")
    public JSONResult addUser(@RequestBody UserWithDetails details){
        removeCache();
        userService.addUserAndDetails(details);
        return JSONResult.ok("新增成功");
    }

    /**
     * 修改用户基本信息
     * @return JSONResult
     */
    @PutMapping("/")
    @SysLog(description ="修改用户信息")
    public JSONResult updateUser(@RequestBody UpdateUserDto updateUserDto){
        removeCache();
        userService.updateUserWithDetails(updateUserDto);
        return JSONResult.ok();
    }

    @DeleteMapping("/{userId}")
    @SysLog(description ="删除用户信息")
    public JSONResult removeUser(@PathVariable String userId){
        removeCache();
        userService.deleteUserAndDetails(Long.parseLong(userId));
        return JSONResult.ok();
    }

    @PutMapping("/status")
    @SysLog(description ="启停用户")
    public JSONResult updateUserStatus(UpdateOrDeleteUserDto userStatusVo){
        removeCache();
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
        String key = keyName+ SecurityUntil.getTenantId()+":like:"+likeName;
        if (redisOperator.exists(key)){
           return JSONResult.ok(redisOperator.getObj(key));
        }else {
            List<UserWithDetailsVo> userList = userService.findUserWithLikeName(likeName);
            redisOperator.setObj(key,userList,24);
            return JSONResult.ok(userList);
        }
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode(){
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = "loginCode:"+UUID.randomUUID().toString();
        // 保存
        redisOperator.set(uuid, result,120);
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("status",200);
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }


    public void removeCache(){
        String key = keyName+ SecurityUntil.getTenantId()+":";
        Set<String> keys = redisOperator.keys(key+"*");
        redisOperator.delKeys(keys);
    }
}
