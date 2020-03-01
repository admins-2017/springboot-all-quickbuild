package com.kang.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/15 10:47
 */
@Data
public class MerchantCommodityCategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "category_id" , type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "菜系/饮料等类别名称")
    private String categoryName;

    @ApiModelProperty(value = "分类状态  1正常 2为删除")
    private Integer categoryStatus;

    @ApiModelProperty(value = "分类父级id")
    private Integer categoryPid;

    @ApiModelProperty(value = "分类等级")
    private Integer categoryLevel;

    private List<MerchantCommodityCategoryVo> children = new ArrayList<MerchantCommodityCategoryVo>();
}
