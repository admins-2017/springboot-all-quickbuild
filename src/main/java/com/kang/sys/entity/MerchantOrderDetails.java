package com.kang.sys.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantOrderDetails对象", description="")
public class MerchantOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detailsId;

    @ApiModelProperty(value = "对应订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "下单商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "购买数量")
    private Integer buyNumber;

    @ApiModelProperty(value = "折扣前金额")
    private BigDecimal beforeDiscount;

    @ApiModelProperty(value = "折扣后金额")
    private BigDecimal afterDiscount;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;


}
