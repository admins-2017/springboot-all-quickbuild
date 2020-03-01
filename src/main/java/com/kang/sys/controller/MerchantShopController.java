package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.MerchantShopDto;
import com.kang.sys.entity.MerchantShop;
import com.kang.sys.enums.SysEnum;
import com.kang.sys.service.IMerchantInventoryService;
import com.kang.sys.service.IMerchantShopService;
import com.kang.sys.vo.ShopWithCommodity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private IMerchantInventoryService inventoryService;

    @ApiOperation(value = "新增商铺",notes = "添加商铺，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    public JSONResult addShop(@RequestBody MerchantShopDto merchantShop){
        MerchantShop shop = new MerchantShop();
        BeanUtils.copyProperties(merchantShop,shop);
        shop.setShopId(Long.parseLong(IdRandom.getRandom()));
        boolean save = shopService.save(shop);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "删除商铺",notes = "根据商铺id获取删除对应的商铺")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商铺id",dataType = "int")
    )
    @DeleteMapping("/{sid}")
    public JSONResult deleteShop(@PathVariable(value = "sid") Long sid){
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
    public JSONResult restShop(@PathVariable(value = "sid") Long sid){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.set("shop_status", SysEnum.shopRest.getCode()).eq("shop_id",sid);
        boolean update = shopService.update(wrapper);
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "开启商铺",notes = "根据商铺id获取将对应的商铺休息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "sid",value = "商铺id",dataType = "int")
    )
    @DeleteMapping("/start/{sid}")
    public JSONResult startShop(@PathVariable(value = "sid") Long sid){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.set("shop_status", SysEnum.shopNormal.getCode()).eq("shop_id",sid);
        boolean update = shopService.update(wrapper);
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "修改商铺",notes = "修改商铺，根据商铺id修改商铺信息")
    @PutMapping("/")
    public JSONResult updateShop(@RequestBody MerchantShopDto shop){
        UpdateWrapper<MerchantShop> wrapper = new UpdateWrapper<>();
        wrapper.eq("shop_id",shop.getShopId());
        MerchantShop merchantShop = new MerchantShop();
        BeanUtils.copyProperties(shop,merchantShop);
        boolean update = shopService.update(merchantShop,wrapper);
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
        IPage<MerchantShop> merchantShopIPage = shopService.page(shopPage);
        return JSONResult.ok(merchantShopIPage);
    }

    @ApiOperation(value = "获取商铺下所有商品",notes = "根据商铺id获取对应的商铺信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
            @ApiImplicitParam(name = "shopId",value = "商品分类id",dataType = "int"),
    })
    @GetMapping("/{page}/{size}/{shopId}")
    public JSONResult getCommodityWithShop(@PathVariable Integer page,@PathVariable Integer size,@PathVariable Long shopId){
        Page<ShopWithCommodity> shopWithCommodityPage = new Page<>(page,size);
        IPage<ShopWithCommodity> iPage= inventoryService.getCommodityWithShopById(shopWithCommodityPage,shopId);
        return JSONResult.ok(iPage);
    }
}
