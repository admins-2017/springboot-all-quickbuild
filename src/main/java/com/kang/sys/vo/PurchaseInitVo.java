package com.kang.sys.vo;

import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import com.kang.sys.vo.purchase.PurchaseShopVo;
import com.kang.sys.vo.purchase.PurchaseSupplierVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/23 14:54
 */
@Data
public class PurchaseInitVo {

    @ApiModelProperty(value = "订单编号")
    private String purchaseNumber;

    @ApiModelProperty(value = "供应商列表")
    private List<PurchaseSupplierVo> supplierList = new ArrayList<>();

    @ApiModelProperty(value = "商铺列表")
    private List<PurchaseShopVo> shopList = new ArrayList<>();

    @ApiModelProperty(value = "商品列表")
    private List<PurchaseCommodityVo> commodityList = new ArrayList<>();

}
