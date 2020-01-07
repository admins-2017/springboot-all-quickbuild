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
 * 商品分类表
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantCommodityCategory对象", description="")
public class MerchantCommodityCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "菜系/饮料等类别名称")
    private String categoryName;

    @ApiModelProperty(value = "分类状态  1正常 2为删除")
    private Integer categoryStatus;

    @ApiModelProperty(value = "新增商品分类时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "新增商品分类用户")
    @TableField(fill = FieldFill.INSERT)
    private Integer insertUser;

    @ApiModelProperty(value = "修改商品分类时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改商品分类用户")
    @TableField(fill = FieldFill.UPDATE)
    private Integer updateUser;

    @ApiModelProperty(value = "租户标记")
    private Integer tenantId;

}
