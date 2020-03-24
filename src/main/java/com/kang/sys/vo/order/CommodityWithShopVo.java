package com.kang.sys.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityWithShopVo {

    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编号")
    private String commodityNumber;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal commodityPrice;

    @ApiModelProperty(value = "商品描述")
    private String commodityDescription;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryNumber;

}
