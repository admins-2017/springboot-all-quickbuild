package com.kang.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/7 16:22
 */
@Data
public class LoginSuccessVo {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 多租户标示
     */
    private Long tenantId;

    private String token;

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

    @ApiModelProperty(value = "所属商铺名称")
    private String shopName;

}
