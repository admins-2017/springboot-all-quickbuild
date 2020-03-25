package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.IdRandom;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    private String keyName="order:";

    @Autowired
    private IMerchantOrderService orderService;
    @Autowired
    private RedisOperator redisOperator;

    /**
     * PC端添加销售单
     * @return
     */
    @PostMapping("/")
    public JSONResult addOrder(@RequestBody InsertOrderDto orderDto){
        clearCache();
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
        String key =keyName+ SecurityUntil.getTenantId()+":getPurchaseOrderWithStatus:"+pStatus+":"+page+":"+size;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        Page<OrderWithDetailsVo> dtoPage = new Page<>(page, size);
        IPage<OrderWithDetailsVo> pageWithStatus = orderService.getPageWithStatus(dtoPage, pStatus);
        redisOperator.setObj(key,pageWithStatus,3);
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String key =keyName+ SecurityUntil.getTenantId()+":init";
        if (redisOperator.exists(key)){
            OrderInitVo vo =(OrderInitVo)redisOperator.getObj(key);
            vo.setOrderNumber(sdf.format(new Date())+ IdRandom.getRandom());
            return JSONResult.ok(vo);
        }
        OrderInitVo vo = orderService.initVo();

        redisOperator.setObj(key,vo,3600);
        //生成订单号
        vo.setOrderNumber(sdf.format(new Date())+ IdRandom.getRandom());

        return JSONResult.ok(vo);
    }


    @ApiOperation(value = "根据商铺查询所有商品及库存",notes = "根据商铺查询所有商品及库存")
    @GetMapping("/getCommodity/{shopId}")
    public JSONResult getCommodityByShopId(@PathVariable Long shopId){
        String key =keyName+ SecurityUntil.getTenantId()+":getCommodityByShopId:"+shopId;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }
        List<CommodityWithShopVo> vo = orderService.getCommodityByShopId(shopId);
        redisOperator.setObjSeconds(key,vo,3600);
        return JSONResult.ok(vo);
    }

    /**
     * 客户下单
     * @return
     */
    public JSONResult initiateOrder(){
        return JSONResult.ok();
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
