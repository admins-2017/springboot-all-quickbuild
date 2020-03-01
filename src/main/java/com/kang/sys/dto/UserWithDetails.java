package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/8 16:53
 */
@Data
public class UserWithDetails {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String userDetailsUrl;

    @ApiModelProperty(value = "用户性别 1 男 2女")
    private Integer userDetailsSex;

    @ApiModelProperty(value = "用户住址")
    private String userDetailsAddr;

    @ApiModelProperty(value = "用户邮箱")
    private String userDetailsMail;

    @ApiModelProperty(value = "用户联系方式")
    private String userDetailsTel;

    @ApiModelProperty(value = "商铺id")
    private Integer shopId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
