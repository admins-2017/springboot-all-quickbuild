package com.kang.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/23 13:33
 */
@Data
public class PurchaseDetailsVo {

    private Integer detailsId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编号")
    private String commodityNumber;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "进货数量")
    private Integer purchaseQuantity;

    @ApiModelProperty(value = "参考价格")
    private BigDecimal referUnitPrice;

    @ApiModelProperty(value = "折扣前金额")
    private BigDecimal beforeDiscount;

    @ApiModelProperty(value = "折扣后金额")
    private BigDecimal afterDiscount;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;
}
