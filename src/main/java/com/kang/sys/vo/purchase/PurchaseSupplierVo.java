package com.kang.sys.vo.purchase;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/23 14:58
 */
@Data
public class PurchaseSupplierVo {

    private Integer supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;

    @ApiModelProperty(value = "开户行")
    private String supplierBankAccount;

    @ApiModelProperty(value = "银行账号")
    private String supplierBankNumber;
}
