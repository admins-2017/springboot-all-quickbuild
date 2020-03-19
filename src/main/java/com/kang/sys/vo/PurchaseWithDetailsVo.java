package com.kang.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kang.sys.entity.MerchantPurchaseDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/19 15:56
 */
@Data
public class PurchaseWithDetailsVo {

    private Long purchaseId;

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

    @ApiModelProperty(value = "供应商")
    private String supplierName;

    @ApiModelProperty(value = "进货店铺")
    private String shopName;

    @ApiModelProperty(value = "添加进货单用户")
    private String username;

    @ApiModelProperty(value = "添加订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "多个商品购买记录")
    private List<PurchaseDetailsVo> children= new ArrayList<>();
}
