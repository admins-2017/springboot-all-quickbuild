package com.kang.sys.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 * 商品实体
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantCommodity对象", description="")
public class MerchantCommodity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "commodity_id",type = IdType.AUTO)
    private Integer commodityId;

    @ApiModelProperty(value = "商品名")
    private String commodityName;

    @ApiModelProperty(value = "商品图片")
    private String commodityPicture;

    @ApiModelProperty(value = "商品编码")
    private String commodityNumber;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal commodityPrice;

    @ApiModelProperty(value = "商品单位(包，份)")
    private String commodityUnit;

    @ApiModelProperty(value = "商品状态 0为正常 1为下架 2为删除")
    private Integer commodityStatus;

    @ApiModelProperty(value = "商品描述")
    private String commodityDescription;

    @ApiModelProperty(value = "商品分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "新增商品时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "新增商品用户")
    @TableField(fill = FieldFill.INSERT)
    private Long insertUser;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改商品用户")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "租户标记")
    private Long tenantId;


}
