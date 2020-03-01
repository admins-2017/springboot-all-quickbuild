package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于用户分配角色
 * @author kang
 * @version 1.0
 * @date 2020/1/16 10:27
 */
@Data
public class AssigningRolesDto {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

}
