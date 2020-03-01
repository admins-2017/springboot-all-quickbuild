package com.kang.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/18 14:18
 */
@Data
public class ShopWithCommodity {

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "商铺状态 1:正常 2:休息 3:作废")
    private String shopStatus;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryNumber;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品名")
    private String commodityNumber;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal commodityPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;
}
