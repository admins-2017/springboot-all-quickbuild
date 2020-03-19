package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.ParseMenuTreeUtil;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.RoleMenu;
import com.kang.sys.service.IMenuService;
import com.kang.sys.service.IRoleMenuService;
import com.kang.sys.service.IUserService;
import com.kang.sys.vo.MenuTreeVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/menu")
@Api("权限详情")
@Slf4j
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 根据权限动态加载功能栏
     */
    @GetMapping("/tree")
    @SysLog(description ="根据权限动态加载功能栏")
    public JSONResult generateFunctionBarWithMenu(){
        String keyName = "menu:"+SecurityUntil.getUserId();

        if (redisOperator.exists(keyName)){
            Object obj = redisOperator.getObj(keyName);
            return JSONResult.ok(obj);
        }else {
            List<MenuTreeVo> menus = userService.selectMenuTreeByUserId(SecurityUntil.getUserId());
            List<MenuTreeVo> menuList = ParseMenuTreeUtil.parseMenuTree(menus);
            redisOperator.setObj("menu:" + SecurityUntil.getUserId(), menuList, 24);
            return JSONResult.ok(menuList);
        }

    }

    /**
     * 查询当前租户下所有权限
     */
    @GetMapping("/")
    @SysLog(description ="查询当前租户下所有权限")
    @PreAuthorize("hasPermission('/menu','sys:menu:list')")
    public JSONResult getAllMenuByTenantId(){
        String keyName= "menu:tenant:"+SecurityUntil.getTenantId();
        log.info("keyName:{}",keyName);
        if (redisOperator.exists(keyName)){
            return JSONResult.ok(redisOperator.getObj(keyName));
        }else {
            List<Menu> list = menuService.list(new LambdaQueryWrapper<Menu>().eq(Menu::getTenantId, SecurityUntil.getTenantId()));
            redisOperator.setObj(keyName,list,18);
            return JSONResult.ok(list);
        }
    }

    /**
     * 为角色赋权限
     * @return JSONResult
     */
    @PostMapping("assignPermissions")
    @SysLog(description ="为角色添加权限")
    public JSONResult addPermissionsWithRole(@RequestBody List<RoleMenu> roleMenuList){
        roleMenuService.saveOrUpdateBatch(roleMenuList);
        return JSONResult.ok();
    }
}
