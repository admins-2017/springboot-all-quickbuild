package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.entity.MerchantCommodity;
import com.kang.sys.enums.SysEnum;
import com.kang.sys.service.IMerchantCommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;


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
@Api(value = "商品controller",tags = "商品对应操作的controller")
public class MerchantCommodityController {

    @Autowired
    private IMerchantCommodityService commodityService;

    @ApiOperation(value = "获取商品",notes = "根据商品id获取对应的商品")
    @ApiImplicitParams(
       @ApiImplicitParam(name = "cid",value = "商品id",dataType = "int")
    )
    @GetMapping("/{cid}")
    @PreAuthorize("hasPermission('21','sys:user:info')")
    @SysLog(description ="获取所有上架商品")
    public JSONResult getCommodity(@PathVariable Integer cid){
        return JSONResult.ok(commodityService.getById(cid));
    }

    @ApiOperation(value = "获取所有上架商品",notes = "调用该接口获取当前用户下所有上架商品")
    @GetMapping("/shelf")
    public JSONResult getCommodityAllWithShelfCommodity(){
        System.out.println("上架："+SysEnum.commodityShelf);
        List<MerchantCommodity> commodityList = commodityService.list(
                new QueryWrapper<MerchantCommodity>().eq("commodity_status", SysEnum.commodityShelf.getCode()));
        if (commodityList.size()>0) {
            return JSONResult.ok(commodityList);
        }else {
            return JSONResult.errorNofind("未找到相关信息");
        }
    }

    @ApiOperation(value = "获取所有下架商品",notes = "调用该接口获取当前用户下所有下架商品")
    @GetMapping("/obtained")
    public JSONResult getCommodityAllWithObtainedCommodity(){
        List<MerchantCommodity> commodityList = commodityService.list(
                new QueryWrapper<MerchantCommodity>().eq("commodity_status", SysEnum.commodityObtained.getCode()));
        if (commodityList.size()>0) {
            return JSONResult.ok(commodityList);
        }else {
            return JSONResult.errorNofind("未找到相关信息");
        }
    }

    @ApiOperation(value = "获取历史删除商品",notes = "调用该接口获取当前用户下所有历史商品")
    @GetMapping("/delete")
    public JSONResult getCommodityAllWithDeleteCommodity(){
        List<MerchantCommodity> commodityList = commodityService.list(
                new QueryWrapper<MerchantCommodity>().eq("commodity_status", SysEnum.commodityDelete.getCode()));
        if (commodityList.size()>0) {
            return JSONResult.ok(commodityList);
        }else {
            return JSONResult.errorNofind("未找到相关信息");
        }
    }

    @ApiOperation(value = "获取分页商品信息",notes = "根据分页条件获取对应的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/{page}/{size}")
    public JSONResult getCommodityWithPage(@PathVariable Integer page,@PathVariable Integer size){
        Page<MerchantCommodity> pages=new Page<>(page,size);
        IPage<MerchantCommodity> commodityIPage = commodityService.page(pages);
        return JSONResult.ok(commodityIPage);
    }

    @ApiOperation(value = "新增商品",notes = "添加商品，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    public JSONResult addCommodity(MerchantCommodity merchantCommodity,HttpServletRequest request){
        boolean save = commodityService.save(merchantCommodity);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "修改商品",notes = "根据商品修改对应的商品")
    @PutMapping("/")
    public JSONResult updateCommodity(MerchantCommodity merchantCommodity){
        boolean update = commodityService.update(merchantCommodity, new UpdateWrapper<MerchantCommodity>()
                .eq("commodity_id",merchantCommodity.getCommodityId()));
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "删除商品",notes = "根据商品id修改对应的商品状态为2 删除标记")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商品id")
    )
    @DeleteMapping("/{cid}")
    public JSONResult deleteCommodity(@PathVariable Integer cid){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Integer updateUser=new Long((Long)request.getSession().getAttribute("user_id")).intValue();
        boolean update = commodityService.update(new UpdateWrapper<MerchantCommodity>().set("commodity_status", 2)
                .set("update_time", LocalDateTime.now()).set("update_user",updateUser)
                .eq("commodity_id", cid));
        return JSONResult.ok(update);
    }
}
