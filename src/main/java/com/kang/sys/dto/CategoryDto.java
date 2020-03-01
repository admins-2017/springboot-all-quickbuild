package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/15 13:49
 */
@Data
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜系/饮料等类别名称")
    private String categoryName;

    @ApiModelProperty(value = "分类状态  1正常 2为删除")
    private Integer categoryStatus;

    @ApiModelProperty(value = "分类父级id")
    private Integer categoryPid;

    @ApiModelProperty(value = "分类等级")
    private Integer categoryLevel;
}
