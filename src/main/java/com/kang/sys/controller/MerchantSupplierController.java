package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.json.JSONResult;
import com.kang.sys.dto.InsertSupplierDto;
import com.kang.sys.entity.MerchantSupplier;
import com.kang.sys.service.IMerchantSupplierService;
import com.kang.sys.vo.SupplierVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/supplier")
@Api(value = "供应商对应的controller",tags = "供应商操作类")
public class MerchantSupplierController {

    @Autowired
    private IMerchantSupplierService supplierService;

    @ApiOperation(value = "新增供应商信息",notes = "添加供应商")
    @PostMapping("/")
    public JSONResult addSupplier(@RequestBody InsertSupplierDto supplierDto){
        MerchantSupplier supplier = new MerchantSupplier();
        BeanUtils.copyProperties(supplierDto,supplier);
        supplierService.save(supplier);
        return JSONResult.ok("添加成功");
    }

    @ApiOperation(value = "根据id修改供应商信息",notes = "修改供应商")
    @PutMapping("/")
    public JSONResult updateSupplier(MerchantSupplier supplier){
        supplierService.update(supplier,new UpdateWrapper<MerchantSupplier>()
                    .eq("supplier_id",supplier.getSupplierId()));
        return JSONResult.ok("更新成功");
    }

    @GetMapping("/")
    public JSONResult getSupplier(){
        return JSONResult.ok();
    }

    @ApiOperation(value = "获取所有删除的供应商",notes = "获取所有历史供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/history/{page}/{size}")
    public JSONResult getSupplierAllWithExpired(@PathVariable Integer page,@PathVariable Integer size){
        Page<MerchantSupplier> supplierPage = new Page<>(page,size);
        IPage<MerchantSupplier> iPage = supplierService.page(supplierPage,
                new QueryWrapper<MerchantSupplier>().eq("supplier_status",0));
        return JSONResult.ok(iPage);
    }

    @ApiOperation(value = "获取所有的供应商",notes = "获取所有供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "记录条数"),
    })
    @GetMapping("/{page}/{size}")
    public JSONResult getSupplierAll(@PathVariable Integer page,@PathVariable Integer size){
        Page<MerchantSupplier> supplierPage = new Page<>(page,size);
        IPage<MerchantSupplier> iPage = supplierService.page(supplierPage,
                new QueryWrapper<MerchantSupplier>().eq("supplier_status",1));
        return JSONResult.ok(iPage);
    }

    @ApiOperation(value = "删除供应商",notes = "删除供应商")
    @DeleteMapping("/{supplierId}")
    public JSONResult delSupplier(@PathVariable Integer supplierId){
        supplierService.update(new UpdateWrapper<MerchantSupplier>()
                .set("supplier_status",0).eq("supplier_id",supplierId));
        return JSONResult.ok("删除成功");
    }

}
