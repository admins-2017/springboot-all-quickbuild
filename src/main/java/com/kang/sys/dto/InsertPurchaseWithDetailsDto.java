package com.kang.sys.dto;

import com.kang.sys.entity.MerchantPurchaseDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/19 15:56
 */
@Data
public class InsertPurchaseWithDetailsDto implements Serializable {

    @ApiModelProperty(value = "订单编号")
    private String purchaseNumber;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal purchaseTotalAmount;

    @ApiModelProperty(value = "订单状态 1 进货 0 退货")
    private Boolean purchaseStatus;

    @ApiModelProperty(value = "订单已支付金额")
    private BigDecimal purchasePay;

    @ApiModelProperty(value = "订单未支付金额")
    private BigDecimal purchaseUnpaid;

    @ApiModelProperty(value = "是否结清标识 1 已结清 0未结清")
    private Boolean purchaseSettleStatus;

    @ApiModelProperty(value = "支付类型 1 现金 2 转账 3 微信 4 支付宝")
    private Integer purchasePayType;

    @ApiModelProperty(value = "供应商id")
    private Integer supplierId;

    @ApiModelProperty(value = "进货店铺")
    private Long shopId;

    @ApiModelProperty(value = "多个商品购买记录")
    private List<MerchantPurchaseDetails> children= new ArrayList<>();
}
