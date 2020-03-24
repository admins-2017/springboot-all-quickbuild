package com.kang.sys.vo.order;

import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import com.kang.sys.vo.purchase.PurchaseShopVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/23 15:12
 */
@Data
public class OrderInitVo {

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "供应商列表")
    private List<OrderClientVo> clientList = new ArrayList<>();

    @ApiModelProperty(value = "商铺列表")
    private List<PurchaseShopVo> shopList = new ArrayList<>();

}
