package com.kang.sys.enums;

/**
 * @author kang
 * @date 2019.11.19
 */

public enum SysEnum {
    /**
     * 商品上架
     */
    commodityShelf("0"),

    /**
     * 商品下架
     */
    commodityObtained("1"),

    /**
     * 商品删除
     */
    commodityDelete("2"),

    /**
     * 商品分类正常
     */
    commodityNormalCategory("2"),

    /**
     * 商品分类删除
     */
    commodityCategoryDelete("2"),

    /**
     * 商铺状态正常
     */
    shopNormal("1"),

    /**
     * 商铺状态休息
     */
    shopRest("2"),

    /**
     * 商铺状态作废
     */
    shopObsolete("3");

    private String code;

    SysEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
