package com.kang.sys.entity;

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
@ApiModel(value="MerchantShopDesk对象", description="")
public class MerchantShopDesk implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deskId;

    @ApiModelProperty(value = "店铺桌号")
    private Integer deskNumber;

    @ApiModelProperty(value = "店铺桌号二维码链接")
    private String deskUrl;

    @ApiModelProperty(value = "对应店铺id")
    private Integer shopId;

    @ApiModelProperty(value = "多租户标示")
    private Long tenantId;


}
