package com.kang.sys.controller;


import com.kang.imploded.json.JSONResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/sys/order-details")
public class OrderDetailsController {

    @ApiOperation(value = "新增商品",notes = "添加商品，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    public JSONResult addCategory(){
        return JSONResult.ok();
    }

    @ApiOperation(value = "获取商品",notes = "根据商品id获取对应的商品")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商品id",dataType = "int")
    )
    @GetMapping("/{cid}")
    public JSONResult deleteCategory(){
        return JSONResult.ok();
    }

    @ApiOperation(value = "新增商品",notes = "添加商品，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PutMapping("/")
    public JSONResult updateCategory(){
        return JSONResult.ok();
    }

    @ApiOperation(value = "获取商品",notes = "根据商品id获取对应的商品")
    @GetMapping("/")
    public JSONResult getAllCategory(){
        return JSONResult.ok();
    }

    @ApiOperation(value = "获取商品",notes = "根据商品id获取对应的商品")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "categoryId",value = "商品分类id",dataType = "int")
    )
    @GetMapping("/{categoryId}")
    public JSONResult getCommodityWithCategory(){
        return JSONResult.ok();
    }
}
