package com.kang.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantPurchaseDetails对象", description="")
public class MerchantPurchaseDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "details_id", type = IdType.AUTO)
    private Integer detailsId;

    @ApiModelProperty(value = "对应进货单的编号")
    private String purchaseNumber;

    @ApiModelProperty(value = "商品对应id")
    private Integer commodityId;

    @ApiModelProperty(value = "进货数量")
    private Integer purchaseQuantity;

    @ApiModelProperty(value = "参考价格")
    private BigDecimal referUnitPrice;

    @ApiModelProperty(value = "折扣前金额")
    private BigDecimal beforeDiscount;

    @ApiModelProperty(value = "折扣后金额")
    private BigDecimal afterDiscount;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;


}
