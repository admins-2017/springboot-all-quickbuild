package com.kang.sys.enums;

import com.kang.imploded.security.until.SecurityUntil;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/18 16:18
 */
public enum RedisIndexEnum {

    /**
     * 商品上架
     */
    indexDetailsRedisKey("index-details:"+ SecurityUntil.getTenantId()),


    /**
     * 商品删除
     */
    commodityDelete("");

    private String code;

    RedisIndexEnum(String code){
        this.code=code;
    }

    public String getCode() {
        return this.code;
    }
}
