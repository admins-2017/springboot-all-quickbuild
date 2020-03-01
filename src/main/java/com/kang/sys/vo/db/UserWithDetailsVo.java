package com.kang.sys.vo.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author kang
 */
@Data
public class UserWithDetailsVo {

    @TableField("user_id")
    private Long userId;
    @TableField("username")
    private String username;
    @TableField("status")
    private String status;
    @TableField("role_name")
    private String roleName;
    @TableField("role_description")
    private String roleDescription;
    @TableField("user_details_sex")
    private Integer userDetailsSex;
    @TableField("user_details_mail")
    private String userDetailsMail;
    @TableField("user_details_tel")
    private String userDetailsTel;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("role_code")
    private String roleCode;
    @TableId(value = "role_id")
    private Long roleId;
}
