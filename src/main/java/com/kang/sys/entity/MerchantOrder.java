package com.kang.sys.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@ApiModel(value="MerchantOrder对象", description="")
public class MerchantOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderTotalAmount;

    @ApiModelProperty(value = "订单状态 1 销售 2 退货")
    private Boolean orderStatus;

    @ApiModelProperty(value = "订单类型 1 pc填写 2 客户发起")
    private Boolean orderType;

    @ApiModelProperty(value = "订单已支付金额")
    private BigDecimal orderPay;

    @ApiModelProperty(value = "订单未支付金额")
    private BigDecimal orderUnpaid;

    @ApiModelProperty(value = "是否结清标识 1 已结清 2 未结清")
    private Boolean orderSettleStatus;

    @ApiModelProperty(value = "支付类型 1 现金 2 转账 3 微信 4 支付宝")
    private Integer orderPayType;

    @ApiModelProperty(value = "订单备注")
    private String orderRemark;

    @ApiModelProperty(value = "下单商铺id")
    private Integer shopId;

    @ApiModelProperty(value = "客户id")
    private Integer clientId;

    private Long insertUser;

    private LocalDateTime insertTime;

    @ApiModelProperty(value = "多租户id")
    private Long tenantId;


}
