package com.kang.sys.entity;

import java.math.BigDecimal;
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
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MerchantClient对象", description="")
public class MerchantClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clientId;

    @ApiModelProperty(value = "客户名称")
    private String clientName;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "开户行")
    private String clientBankAccount;

    @ApiModelProperty(value = "银行账号")
    private String clientBankNumber;

    @ApiModelProperty(value = "客户状态")
    private Boolean clientStatus;

    @ApiModelProperty(value = "客户所欠金额")
    private BigDecimal clientArrears;

    @ApiModelProperty(value = "新增用户")
    private Long insertUser;

    @ApiModelProperty(value = "新增时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改用户id")
    private Long updateUser;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "租户标示")
    private Long tenantId;


}
