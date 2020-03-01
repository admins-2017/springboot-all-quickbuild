package com.kang.sys.vo;

import lombok.Data;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/7 16:22
 */
@Data
public class LoginSuccessVo {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;

    private String email;

    private String mobile;

    private Integer tenantId;

    private String token;

}
