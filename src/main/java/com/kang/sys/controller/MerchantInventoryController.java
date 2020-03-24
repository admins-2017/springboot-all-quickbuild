package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.entity.MerchantInventory;
import com.kang.sys.service.IMerchantInventoryService;
import io.swagger.annotations.Api;
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
@RequestMapping("/inventory")
@Api(value = "库存controller",tags = "库存对应操作")
public class MerchantInventoryController {

    @Autowired
    private IMerchantInventoryService inventoryService;

    @ApiOperation(value = "新商品入库",notes = "入库")
    @PostMapping("/")
    public JSONResult addInventory(@RequestBody MerchantInventory merchantInventory){
        boolean save = inventoryService.save(merchantInventory);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "增加库存",notes = "根据库存id增加库存数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "增加数量", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "库存id", dataType = "int"),
    })
    @PutMapping("/increase/{number}/{id}")
    public JSONResult increaseInventory(@PathVariable(value = "number") Integer number,@PathVariable(value = "id") Integer id){
        inventoryService.increaseInventory(number,id);
        return JSONResult.ok();
    }


    @ApiOperation(value = "减去库存",notes = "根据库存id减去库存数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "减去数量", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "库存id", dataType = "int"),
    })
    @PutMapping("/minus/{number}/{id}")
    public JSONResult minusInventory(@PathVariable(value = "number") Integer number,@PathVariable(value = "id") Integer id){
        UpdateWrapper<MerchantInventory> wrapper = new UpdateWrapper<>();
        wrapper.setSql("inventory_number=(inventory_number - "+number+")").eq("inventory_id",id);
        inventoryService.update(wrapper);
        return JSONResult.ok();
    }


}
