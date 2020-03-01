package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/18 12:22
 */
@Data
public class MerchantShopDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

}
