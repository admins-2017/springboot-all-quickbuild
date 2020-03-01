package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.entity.UserDetails;
import com.kang.sys.service.IUserDetailsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/user/details")
public class UserDetailsController {

    @Autowired
    IUserDetailsService detailsService;

    @ApiOperation(value = "新增用户详情",notes = "添加用户，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    @SysLog(description ="添加用户详情信息")
    public JSONResult addUserDetails(@RequestBody UserDetails userDetails){
        detailsService.save(userDetails);
        return JSONResult.ok();
    }

    @ApiOperation(value = "修改用户详情",notes = "添加用户，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PutMapping("/")
    @SysLog(description ="修改用户详情信息")
    public JSONResult updateUserDetails(@RequestBody UserDetails userDetails){
        detailsService.update(userDetails,new UpdateWrapper<UserDetails>().eq("user_id",userDetails.getUserId()));
        return JSONResult.ok();
    }


}
