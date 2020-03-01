package com.kang.sys.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/11 11:19
 */
@Data
public class UpdateUserDto {

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

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
}
