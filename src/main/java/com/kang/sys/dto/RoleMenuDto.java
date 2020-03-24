package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/24 16:24
 */
@Data
public class RoleMenuDto {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    private List<Long> menuIds;
}
