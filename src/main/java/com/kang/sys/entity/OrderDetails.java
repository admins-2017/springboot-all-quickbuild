package com.kang.sys.entity;

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
 * 订单详情表
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderDetails对象", description="")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "details_id")
    private Integer detailsId;

    @ApiModelProperty(value = "对应订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "下单商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "购买数量")
    private Integer buyNumber;


}
