package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改和删除前端dto
 * @author kang
 * @version 1.0
 * @date 2020/1/9 16:37
 */
@Data
public class UpdateOrDeleteUserDto {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "状态 PROHIBIT：禁用   NORMAL：正常")
    private String status;
}
