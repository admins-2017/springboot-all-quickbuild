package com.kang.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/10 14:41
 */
@Data
public class UpdateOrDeleteRoleDto {

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;

    @ApiModelProperty("角色删除标识 0：使用 ，1：删除")
    @TableField(value = "del_flag")
    private Integer roleDelFlag;
}
