package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.dto.CommodityDto;
import com.kang.sys.entity.MerchantCommodity;
import com.kang.sys.enums.CommodityEnum;
import com.kang.sys.service.IMerchantCommodityService;
import com.kang.sys.vo.db.CommodityWithCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


/**
 * <p>
 *  商品实体
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/commodity")
@Api(value = "商品controller",tags = "商品对应操作")
public class MerchantCommodityController {

    private String keyName="commodity:";

    @Autowired
    private IMerchantCommodityService commodityService;
    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "获取商品",notes = "根据商品id获取对应的商品")
    @ApiImplicitParams(
       @ApiImplicitParam(name = "cid",value = "商品id",dataType = "int")
    )
    @GetMapping("/{cid}")
    @SysLog(description ="查询商品")
    public JSONResult getCommodity(@PathVariable Integer cid){
        String key =keyName+ SecurityUntil.getTenantId()+":getCommodity:"+cid;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        MerchantCommodity byId = commodityService.getById(cid);
        redisOperator.setObjSeconds(key,byId,7200);
        return JSONResult.ok(byId);
    }

    @ApiOperation(value = "获取所有上架商品",notes = "调用该接口获取当前用户下所有上架商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/shelf/{page}/{size}")
    @SysLog(description ="查询所有上架商品")
    public JSONResult getCommodityAllWithShelfCommodity(@PathVariable Integer page,@PathVariable Integer size){
        String key =keyName+ SecurityUntil.getTenantId()+":getCommodityAllWithShelfCommodity:"+page+":"+size;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        Page<CommodityWithCategory> pages=new Page<>(page,size);
        IPage<CommodityWithCategory> commodityList = commodityService.selectByCommodityStatus(pages,CommodityEnum.commodityShelf.getCode());
        redisOperator.setObjSeconds(key,commodityList,36000);
        return JSONResult.ok(commodityList);
    }

    @ApiOperation(value = "获取所有下架商品",notes = "调用该接口获取当前用户下所有下架商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/obtained/{page}/{size}")
    @SysLog(description ="查询所有下架商品")
    public JSONResult getCommodityAllWithObtainedCommodity(@PathVariable Integer page,@PathVariable Integer size){
        String key =keyName+ SecurityUntil.getTenantId()+":getCommodityAllWithObtainedCommodity:"+page+":"+size;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        Page<CommodityWithCategory> pages=new Page<>(page,size);
        IPage<CommodityWithCategory> commodityList = commodityService.selectByCommodityStatus(pages,CommodityEnum.commodityObtained.getCode());
        redisOperator.setObjSeconds(key,commodityList,36000);
        return JSONResult.ok(commodityList);
    }

    @ApiOperation(value = "获取历史删除商品",notes = "调用该接口获取当前用户下所有历史商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/history/{page}/{size}")
    @SysLog(description ="查询历史商品")
    public JSONResult getCommodityAllWithDeleteCommodity(@PathVariable Integer page,@PathVariable Integer size){
        String key =keyName+ SecurityUntil.getTenantId()+":getCommodityAllWithDeleteCommodity:"+page+":"+size;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        Page<CommodityWithCategory> pages=new Page<>(page,size);
        IPage<CommodityWithCategory> commodityList = commodityService.selectByCommodityStatus(pages,CommodityEnum.commodityDelete.getCode());
        redisOperator.setObjSeconds(key,commodityList,36000);
        return JSONResult.ok(commodityList);
    }

    @ApiOperation(value = "新增商品",notes = "添加商品，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    @SysLog(description ="添加商品信息")
    public JSONResult addCommodity(@RequestBody CommodityDto commodityDto){
        clearCache();
        MerchantCommodity merchantCommodity = new MerchantCommodity();
        BeanUtils.copyProperties(commodityDto,merchantCommodity);
        boolean save = commodityService.save(merchantCommodity);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "修改商品",notes = "根据商品修改对应的商品")
    @PutMapping("/")
    @SysLog(description ="修改商品信息")
    public JSONResult updateCommodity(CommodityDto commodityDto){
        clearCache();
        MerchantCommodity merchantCommodity = new MerchantCommodity();
        BeanUtils.copyProperties(commodityDto,merchantCommodity);
        boolean update = commodityService.update(merchantCommodity, new UpdateWrapper<MerchantCommodity>()
                .eq("commodity_id",commodityDto.getUpdateCommodityId()));
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "删除商品",notes = "根据商品id修改对应的商品状态为2 删除标记")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商品id")
    )
    @DeleteMapping("/{cid}")
    @SysLog(description ="删除商品信息")
    public JSONResult updateCommodityStatus(@PathVariable Integer cid){
        clearCache();
        MerchantCommodity merchantCommodity = new MerchantCommodity();
        merchantCommodity.setCommodityStatus(2);
        boolean update = commodityService.update(merchantCommodity,new UpdateWrapper<MerchantCommodity>().eq("commodity_id", cid));
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "模糊查询商品",notes = "根据传值进行模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
            @ApiImplicitParam(name = "likeName", value = "查询条件"),
    })
    @GetMapping("/like/{page}/{size}/{likeName}")
    @SysLog(description ="根据条件查询商品")
    public JSONResult getCommodityWithLikeName(@PathVariable Long page,@PathVariable Long size,@PathVariable String likeName){
        Page<MerchantCommodity> pages=new Page<>(page,size);
        IPage<MerchantCommodity> commodities = commodityService.page(pages,new QueryWrapper<MerchantCommodity>()
                .like("commodity_name", likeName)
                .or().like("commodity_description", likeName)
                .or().like("commodity_number", likeName));
        return JSONResult.ok(commodities);
    }

    public void clearCache(){
        String key =keyName+ SecurityUntil.getTenantId();
        Set<String> keys = redisOperator.keys(key+":*");
        keys.forEach(s -> {
            System.out.println(s);
        });
        redisOperator.delKeys(keys);
    }
}
