package com.kang.sys.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/23 15:13
 */
@Data
public class OrderClientVo {

    private Integer clientId;

    @ApiModelProperty(value = "客户名称")
    private String clientName;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "开户行")
    private String clientBankAccount;

    @ApiModelProperty(value = "银行账号")
    private String clientBankNumber;
}
