package com.kang.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.MerchantOrder;
import com.kang.sys.entity.MerchantPurchase;
import com.kang.sys.entity.MerchantShop;
import com.kang.sys.enums.RedisIndexEnum;
import com.kang.sys.service.IMerchantOrderService;
import com.kang.sys.service.IMerchantPurchaseService;
import com.kang.sys.service.IMerchantShopService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/18 15:20
 */
@RestController
@RequestMapping("/index")
@Api("首页详情")
@Slf4j
public class IndexController {

    @Autowired
    private IMerchantPurchaseService purchaseService;
    @Autowired
    private IMerchantOrderService orderService;
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private IMerchantShopService shopService;



    @GetMapping
    public JSONResult getIndexDetails(){

        String keyName = RedisIndexEnum.indexDetailsRedisKey.getCode();
        if (redisOperator.exists(keyName)){
            Map<String, Object> map = redisOperator.hGetStringAll(keyName);
            return JSONResult.ok(map);
        }else {
            int purchaseCount = purchaseService.count(new QueryWrapper<MerchantPurchase>().eq("purchase_status", 1));
            int orderCount = orderService.count(new QueryWrapper<MerchantOrder>().eq("order_status", 1));
            int startShop = shopService.count(new QueryWrapper<MerchantShop>().eq("shop_status", 1));
            int restShop = shopService.count(new QueryWrapper<MerchantShop>().eq("shop_status", 2));
            Map<String,Object> map = new HashMap<>(4);
            map.put("purchase",purchaseCount);
            map.put("order",orderCount);
            map.put("startShop",startShop);
            map.put("restShop",restShop);
            redisOperator.hPutAll(keyName,map);
            return JSONResult.ok(map);
        }

    }


}
