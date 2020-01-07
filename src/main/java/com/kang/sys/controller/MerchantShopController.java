package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.entity.MerchantShop;
import com.kang.sys.enums.SysEnum;
import com.kang.sys.service.IMerchantShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
@RequestMapping("/shop")
@Api(value = "商铺controller",tags = "商铺对应操作的controller")
public class MerchantShopController {


    @Autowired
    private IMerchantShopService shopService;

    @ApiOperation(value = "新增商铺",notes = "添加商铺，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    public JSONResult addShop(@RequestBody MerchantShop merchantShop){
        boolean save = shopService.save(merchantShop);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "删除商铺",notes = "根据商铺id获取删除对应的商铺")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商铺id",dataType = "int")
    )
    @DeleteMapping("/{sid}")
    public JSONResult deleteShop(@PathVariable(value = "sid") Integer sid){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.set("shop_status", SysEnum.shopObsolete.getCode()).eq("shop_id",sid);
        boolean update = shopService.update(wrapper);
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "商铺休息",notes = "根据商铺id获取将对应的商铺休息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "sid",value = "商铺id",dataType = "int")
    )
    @DeleteMapping("/rest/{sid}")
    public JSONResult restShop(@PathVariable(value = "sid") Integer sid){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.set("shop_status", SysEnum.shopRest.getCode()).eq("shop_id",sid);
        boolean update = shopService.update(wrapper);
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "修改商铺",notes = "修改商铺，根据商铺id修改商铺信息")
    @PutMapping("/")
    public JSONResult updateShop(@RequestBody MerchantShop shop){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.eq("shop_id",shop.getShopId());
        boolean update = shopService.update(shop,wrapper);
        return JSONResult.ok(update);
    }


    @ApiOperation(value = "获取商铺",notes = "获取该用户下所有商铺")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/{page}/{size}")
    public JSONResult getAllShop(@PathVariable(value = "page") Integer page,
                                  @PathVariable(value="size") Integer size){
        Page<MerchantShop> shopPage=new Page<>(page,size);
        IPage<MerchantShop> page1 = shopService.page(shopPage);
        return JSONResult.ok(page1);
    }

    @ApiOperation(value = "获取商铺",notes = "根据商铺id获取对应的商铺信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "shopId",value = "商品分类id",dataType = "int")
    )
    @GetMapping("/{shopId}")
    public JSONResult getCommodityWithShop(@PathVariable Integer shopId){
        MerchantShop byId = shopService.getById(shopId);
        return JSONResult.ok(byId);
    }
}
