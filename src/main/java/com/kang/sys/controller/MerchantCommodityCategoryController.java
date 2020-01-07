package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodity;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.kang.sys.enums.SysEnum;
import com.kang.sys.service.IMerchantCommodityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  商品分类controller
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/category")
@Api(value = "商品分类controller",tags = "商品分类对应操作的controller")
public class MerchantCommodityCategoryController {

    @Autowired
    private IMerchantCommodityCategoryService categoryService;

    @ApiOperation(value = "新增商品分类",notes = "添加商品分类，新增时间，用户，修改时间，用户,租户id不需要添加")
    @PostMapping("/")
    public JSONResult addCategory(@RequestBody MerchantCommodityCategory commodityCategory){
        boolean save = categoryService.save(commodityCategory);
        return JSONResult.ok(save);
    }

    @ApiOperation(value = "删除商品分类",notes = "根据商品分类id删除对应的商品分类")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cid",value = "商品id",dataType = "int")
    )
    @DeleteMapping("/{cid}")
    public JSONResult deleteCategory(@PathVariable Integer cid){
        boolean update = categoryService.update(new UpdateWrapper<MerchantCommodityCategory>().set("category_status",
                SysEnum.commodityCategoryDelete.getCode()).eq("category_id", cid));
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "更新商品分类",notes = "修改分类名称，根据id修改，只需要传值id，name，不需要状态")
    @PutMapping("/")
    public JSONResult updateCategory(@RequestBody MerchantCommodityCategory commodityCategory){
        boolean update = categoryService.update(commodityCategory, new UpdateWrapper<MerchantCommodityCategory>()
                .eq("category_id", commodityCategory.getCategoryId()));
        return JSONResult.ok(update);
    }

    @ApiOperation(value = "获取当前用户下所有商品分类",notes = "获取所有商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/{page}/{size}")
    public JSONResult getAllCategory(@PathVariable(value = "page") Integer page,
                                     @PathVariable(value = "size") Integer size){
        Page<MerchantCommodityCategory> pages=new Page<>(page,size);
        IPage<MerchantCommodityCategory> categoryIPage = categoryService.page(
                pages,new QueryWrapper<MerchantCommodityCategory>().eq("category_status",1));
        return JSONResult.ok(categoryIPage);
    }

    @ApiOperation(value = "获取分类下所有商品",notes = "根据商品分类id获取对应分类所有的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
            @ApiImplicitParam(name = "categoryId", value = "分类id"),
    })
    @GetMapping("/{page}/{size}/{categoryId}")
    public JSONResult getCommodityWithCategory(@PathVariable(value = "page") Integer page,
                                               @PathVariable(value = "size") Integer size,
                                               @PathVariable(value = "categoryId") Integer categoryId){
        Page<MerchantCommodityAndCategoryName> pages=new Page<>(page,size);
        List<MerchantCommodityAndCategoryName> list = categoryService.getCommodityWithCategory(categoryId);
        if (list.isEmpty()){
            return JSONResult.errorNofind("查找无信息");
        }else {
            return JSONResult.ok(list);
        }
    }


}
