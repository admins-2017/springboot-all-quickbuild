package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.dto.InsertOrderDto;
import com.kang.sys.dto.QueryOrderDto;
import com.kang.sys.service.IMerchantOrderService;
import com.kang.sys.vo.order.CommodityWithShopVo;
import com.kang.sys.vo.order.OrderInitVo;
import com.kang.sys.vo.order.OrderWithDetailsVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
@RestController
@RequestMapping("/order")
public class MerchantOrderController {

    @Autowired
    private IMerchantOrderService orderService;

    /**
     * PC端添加销售单
     * @return
     */
    @PostMapping("/")
    public JSONResult addOrder(@RequestBody InsertOrderDto orderDto){
        orderService.insertOrder(orderDto);
        if (orderDto.getOrderStatus()){
            return JSONResult.ok("新增进货单完成");
        }else {
            return  JSONResult.ok("新增退货单完成");
        }
    }


    /**
     * 根据传值进行查询进货/退货 分页
     * @return
     */
    @ApiOperation(value = "根据状态进行查询单据信息",notes = "根据状态进行查询单据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
            @ApiImplicitParam(name = "pStatus", value = "进货/退货"),
    })
    @GetMapping("/status/{page}/{size}/{pStatus}")
    public JSONResult getPurchaseOrderWithStatus(@PathVariable Integer page
            ,@PathVariable Integer size,@PathVariable Boolean pStatus){
            Page<OrderWithDetailsVo> dtoPage = new Page<>(page, size);
            IPage<OrderWithDetailsVo> pageWithStatus = orderService.getPageWithStatus(dtoPage, pStatus);
            return JSONResult.ok(pageWithStatus);
    }

    @ApiOperation(value = "根据条件进行查询单据信息",notes = "根据条件进行查询单据信息")
    @GetMapping("/conditions")
    public JSONResult getPurchaseOrderWithQueryConditions(QueryOrderDto dto){
        IPage<OrderWithDetailsVo> dtoPage=orderService.getPageWithConditions(dto);
        return JSONResult.ok(dtoPage);
    }

    @ApiOperation(value = "初始销售单",notes = "销售单初始化方法")
    @GetMapping("/orderInit")
    public JSONResult orderInit(){
        OrderInitVo vo = orderService.initVo();
        return JSONResult.ok(vo);
    }


    @ApiOperation(value = "根据商铺查询所有商品及库存",notes = "根据商铺查询所有商品及库存")
    @GetMapping("/getCommodity/{shopId}")
    public JSONResult getCommodityByShopId(@PathVariable Long shopId){
        List<CommodityWithShopVo> vo = orderService.getCommodityByShopId(shopId);
        return JSONResult.ok(vo);
    }

    /**
     * 客户下单
     * @return
     */
    public JSONResult initiateOrder(){
        return JSONResult.ok();
    }

}
