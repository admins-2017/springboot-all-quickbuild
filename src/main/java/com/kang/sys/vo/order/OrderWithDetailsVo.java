package com.kang.sys.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/23 15:36
 */
@Data
public class OrderWithDetailsVo {

    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderTotalAmount;

    @ApiModelProperty(value = "订单状态 1 销售 2 退货")
    private Boolean orderStatus;

    @ApiModelProperty(value = "订单类型 1 pc填写 0 客户发起")
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
    private String shopName;

    @ApiModelProperty(value = "客户id")
    private String clientName;

    @ApiModelProperty(value = "添加销售单用户")
    private String username;

    @ApiModelProperty(value = "添加订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "多个商品购买记录")
    private List<OrderDetailsVo> children= new ArrayList<>();
}
