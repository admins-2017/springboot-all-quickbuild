package com.kang.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/18 19:32
 */
@Data
public class SupplierVo {
    private Integer supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;

    @ApiModelProperty(value = "开户行")
    private String supplierBankAccount;

    @ApiModelProperty(value = "银行账号")
    private String supplierBankNumber;

    @ApiModelProperty(value = "供应商状态 1 正常 2删除")
    private Boolean supplierStatus;

    @ApiModelProperty(value = "所欠供货商金额")
    private BigDecimal supplierArrears;
}
