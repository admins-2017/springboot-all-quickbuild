package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/10 12:31
 */
@Data
public class UserRoleDto {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;

}
