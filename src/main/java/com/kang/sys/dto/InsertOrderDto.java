package com.kang.sys.dto;

import com.kang.sys.entity.MerchantOrderDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/23 9:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertOrderDto {

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
    private Long shopId;

    @ApiModelProperty(value = "客户id")
    private Integer clientId;

    private List<MerchantOrderDetails> children = new ArrayList<>();
}
