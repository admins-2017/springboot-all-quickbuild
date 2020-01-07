package com.kang.sys.entity;

import java.time.LocalDateTime;
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
 * @author jobob
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ScheduleJob对象", description="")
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobName;

    private String cronExpression;

    private String beanName;

    private String methodName;

    @ApiModelProperty(value = "状态 1.启动 2.暂停")
    private Integer status;

    @ApiModelProperty(value = "是否删除 0.否 1.是")
    private Boolean deleteFlag;

    @ApiModelProperty(value = "创建人id")
    private Integer creatorId;

    @ApiModelProperty(value = "创建人")
    private String creatorName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
