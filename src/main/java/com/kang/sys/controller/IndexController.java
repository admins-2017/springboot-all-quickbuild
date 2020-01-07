package com.kang.sys.controller;

import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 初始页面
 * @Author Sans
 * @CreateTime 2019/10/2 15:11
 */
@RestController
@RequestMapping("/index")
@Api("首页详情")
public class IndexController {

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 首页
     * @Author Sans
     * @CreateTime 2019/10/2 15:23
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public JSONResult userLogin(){
        String key = redisOperator.get("测试key");
        return JSONResult.ok(key);
    }

}