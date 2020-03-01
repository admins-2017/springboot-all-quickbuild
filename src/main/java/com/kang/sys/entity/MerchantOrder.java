package com.kang.sys.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *  订单实体
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantOrder对象", description="")
public class MerchantOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "order_id")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "订单未打折之前的总价格")
    private BigDecimal orderAllPrice;

    @ApiModelProperty(value = "订单状态 0下单完成 1 结算完成")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单实付价格")
    private BigDecimal orderActualPrice;

    @ApiModelProperty(value = "订单折扣")
    private BigDecimal orderDiscount;

    @ApiModelProperty(value = "订单备注")
    private String orderRemark;

    @ApiModelProperty(value = "下单商铺id")
    private Integer shopId;

    @ApiModelProperty(value = "下单桌号")
    private Integer deskId;

    @ApiModelProperty(value = "多租户id")
    private Long tenantId;


}
