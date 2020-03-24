package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.InsertPurchaseWithDetailsDto;
import com.kang.sys.dto.PurchaseDetailsDto;
import com.kang.sys.dto.QueryPurchaseDto;
import com.kang.sys.enums.RedisIndexEnum;
import com.kang.sys.service.IMerchantPurchaseService;
import com.kang.sys.vo.PurchaseInitVo;
import com.kang.sys.vo.PurchaseWithDetailsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/purchase")
@Api(value = "进货单对应的controller",tags = "进货单操作类")
public class MerchantPurchaseController {

    @Autowired
    private IMerchantPurchaseService purchaseService;

    @Autowired
    private RedisOperator redisOperator;

    private String keyName="shop:merchant-purchase:";

    /**
     * 新增进货单
     * @param dto
     * @return
     */
    @ApiOperation(value = "创建进货单",notes = "新增进货单")
    @SysLog(description ="新增进货单")
    @PostMapping("/")
    public JSONResult addPurchaseOrder(@RequestBody InsertPurchaseWithDetailsDto dto){
            clearCache();
            //更新首页进货量的redis
            redisOperator.hashIncrBy(RedisIndexEnum.indexDetailsRedisKey.getCode(),"purchase",1);
            purchaseService.addPurchaseOrder(dto);
            if (dto.getPurchaseStatus()){
                return JSONResult.ok("新增进货单完成");
            }else {
                return  JSONResult.ok("新增退货单完成");
            }
    }

    /**
     * 作废进货/退货单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public JSONResult invalidPurchaseOrder(@PathVariable Long id){
        return JSONResult.ok();
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
        String key =keyName+ SecurityUntil.getTenantId()+':'+page+':'+size+':'+pStatus;
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }else {
            Page<PurchaseWithDetailsVo> dtoPage = new Page<>(page, size);
            IPage<PurchaseWithDetailsVo> pageWithStatus = purchaseService.getPageWithStatus(dtoPage, pStatus);
            redisOperator.setObj(key,pageWithStatus,3600);
            return JSONResult.ok(pageWithStatus);
        }
    }

    @ApiOperation(value = "根据条件进行查询单据信息",notes = "根据条件进行查询单据信息")
    @GetMapping("/conditions")
    public JSONResult getPurchaseOrderWithQueryConditions(QueryPurchaseDto dto){
        IPage<PurchaseWithDetailsVo> dtoPage=purchaseService.getPageWithConditions(dto);
        return JSONResult.ok(dtoPage);
    }

    @ApiOperation(value = "初始化进货单",notes = "进货单初始化方法")
    @GetMapping("/purchaseInit")
    public JSONResult purchaseInit(){
        String key = keyName+"init:"+SecurityUntil.getTenantId();
        if (redisOperator.exists(key)){
            return JSONResult.ok(redisOperator.getObj(key));
        }else {
            PurchaseInitVo init = purchaseService.getInit();
            redisOperator.setObjSeconds(key,init,3600);
            return JSONResult.ok(init);
        }
    }

    public void clearCache(){
        String key = keyName+ SecurityUntil.getTenantId()+':';
        Set<String> keys = redisOperator.keys(key+"*");
        redisOperator.delKeys(keys);
    }
}
