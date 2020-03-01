package com.kang.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/20 16:08
 */
@Data
public class QueryPurchaseDto {

    @ApiModelProperty(value = "订单编号")
    private String purchaseNumber;

    @ApiModelProperty(value = "是否结清标识 1 已结清 0未结清")
    private Boolean purchaseSettleStatus;

    @ApiModelProperty(value = "支付类型 1 现金 2 转账 3 微信 4 支付宝")
    private Integer purchasePayType;

    @ApiModelProperty(value = "添加订单时间")
    private String insertTime;

    @ApiModelProperty(value = "供应商id")
    private String supplierName;

    @ApiModelProperty(value = "进货店铺")
    private String shopName;

    @ApiModelProperty(value = "进货人员")
    private String username;

    @ApiModelProperty(value = "分页页码")
    private Integer page;

    @ApiModelProperty(value = "分页条数")
    private Integer size;
}
