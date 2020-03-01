package com.kang.sys.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantPurchase对象")
public class MerchantPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "purchase_id", type = IdType.AUTO)
    private Long purchaseId;

    @ApiModelProperty(value = "订单编号")
    private String purchaseNumber;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal purchaseTotalAmount;

    @ApiModelProperty(value = "订单状态 1 进货 2 退货")
    private Boolean purchaseStatus;

    @ApiModelProperty(value = "订单已支付金额")
    private BigDecimal purchasePay;

    @ApiModelProperty(value = "订单未支付金额")
    private BigDecimal purchaseUnpaid;

    @ApiModelProperty(value = "是否结清标识 1 已结清 2 未结清")
    private Boolean purchaseSettleStatus;

    @ApiModelProperty(value = "支付类型 1 现金 2 转账 3 微信 4 支付宝")
    private Integer purchasePayType;

    @ApiModelProperty(value = "供应商id")
    private Integer supplierId;

    @ApiModelProperty(value = "进货店铺")
    private Long shopId;

    @ApiModelProperty(value = "添加订单用户id")
    @TableField(fill = FieldFill.INSERT)
    private Long insertUser;

    @ApiModelProperty(value = "添加订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "更新订单用户id")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "更新时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "租户标识")
    private Long tenantId;


}
