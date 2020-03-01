package com.kang.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *  商品库存
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantInventory对象", description="")
@NoArgsConstructor
public class MerchantInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "inventory_id",type = IdType.AUTO)
    private Integer inventoryId;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryNumber;

    @ApiModelProperty(value = "多租户标示")
    private Long tenantId;


    public MerchantInventory(Long shopId, Integer commodityId, Integer inventoryNumber) {
        this.shopId = shopId;
        this.commodityId = commodityId;
        this.inventoryNumber = inventoryNumber;
    }
}
