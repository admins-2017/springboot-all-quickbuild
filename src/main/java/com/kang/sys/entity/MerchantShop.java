package com.kang.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *  商铺实体
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantShop对象", description="")
public class MerchantShop implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "shop_id")
    private Integer shopId;

    @ApiModelProperty(value = "商铺名称")
    private String shopName;

    @ApiModelProperty(value = "商铺地址")
    private String shopAddress;

    @ApiModelProperty(value = "商铺状态 1:正常 2:休息 3:作废")
    private String shopStatus;

    @ApiModelProperty(value = "新增商铺时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "新增商铺用户")
    @TableField(fill = FieldFill.INSERT)
    private Integer insertUser;

    @ApiModelProperty(value = "修改商铺时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改商铺用户")
    @TableField(fill = FieldFill.UPDATE)
    private Integer updateUser;

    @ApiModelProperty(value = "租户标示")
    private Integer tenantId;


}
