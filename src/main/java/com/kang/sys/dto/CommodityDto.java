package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/6 22:12
 */
@Data
public class CommodityDto {

    @ApiModelProperty(value = "商品ID")
    private Integer updateCommodityId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal commodityPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商品描述")
    private String commodityDescription;

    @ApiModelProperty(value = "商品分类id")
    private Integer categoryId;
}
