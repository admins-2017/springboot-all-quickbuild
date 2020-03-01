package com.kang.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/18 19:25
 */
@Data
public class InsertSupplierDto {

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;

    @ApiModelProperty(value = "开户行")
    private String supplierBankAccount;

    @ApiModelProperty(value = "银行账号")
    private String supplierBankNumber;
}
