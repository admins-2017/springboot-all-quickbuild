package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/23 17:13
 */
@Data
public class QueryOrderDto {

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "是否结清标识 1 已结清 2 未结清")
    private Boolean orderSettleStatus;

    @ApiModelProperty(value = "支付类型 1 现金 2 转账 3 微信 4 支付宝")
    private Integer orderPayType;

    @ApiModelProperty(value = "添加订单时间")
    private String insertTime;

    @ApiModelProperty(value = "客户名")
    private String clientName;

    @ApiModelProperty(value = "进货店铺")
    private String shopName;

    @ApiModelProperty(value = "进货人员")
    private String username;

    @ApiModelProperty(value = "分页页码")
    private Integer page;

    @ApiModelProperty(value = "分页条数")
    private Integer size;
}
