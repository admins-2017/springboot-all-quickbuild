package com.kang.sys.enums;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/6 18:58
 */
public enum CommodityEnum {


    /**
     * 商品上架
     */
    commodityShelf(0),

    /**
     * 商品下架
     */
    commodityObtained(1),

    /**
     * 商品删除
     */
    commodityDelete(2);

    private Integer code;

    CommodityEnum(Integer code){
        this.code=code;
    }

    public Integer getCode() {
        return this.code;
    }
}

