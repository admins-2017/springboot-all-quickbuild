package com.kang.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * 系统操作记录表
 *
 * @author jobob
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_operation_record")
@ApiModel(value="OperationRecord对象", description="")
public class OperationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id",type = IdType.AUTO)
    private Integer recordId;

    @ApiModelProperty(value = "操作类型 get post put del")
    private String requestType;

    @ApiModelProperty(value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(value = "操作员姓名")
    private String requestUser;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime requestTime;

    @ApiModelProperty(value = "操作ip")
    private String requestIp;

    @ApiModelProperty(value = "操作描述")
    private String description;

    @ApiModelProperty(value = "请求的方法")
    @TableField("requestMethod")
    private String requestMethod;

    @ApiModelProperty(value = "多租户标示")
    private Long tenantId;


}
