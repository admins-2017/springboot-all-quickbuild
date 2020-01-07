package com.kang.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 用户详情表
 * @author jobob
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_details")
@ApiModel(value="UserDetails对象", description="")
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_details_id")
    private Integer userDetailsId;

    @ApiModelProperty(value = "用户头像")
    private String userDetailsUrl;

    @ApiModelProperty(value = "用户性别 1 男 2女")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

    @ApiModelProperty(value = "商铺id")
    private Integer shopId;

    @ApiModelProperty(value = "多租户标示")
    private Integer tenantId;


}
