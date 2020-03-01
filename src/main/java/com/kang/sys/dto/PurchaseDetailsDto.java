package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/19 15:58
 */
@Data
public class PurchaseDetailsDto {

    @ApiModelProperty(value = "商品对应id")
    private Integer commodityId;

    @ApiModelProperty(value = "进货数量")
    private Integer purchaseQuantity;

    @ApiModelProperty(value = "折扣前金额")
    private BigDecimal beforeDiscount;

    @ApiModelProperty(value = "折扣后金额")
    private BigDecimal afterDiscount;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;
}
