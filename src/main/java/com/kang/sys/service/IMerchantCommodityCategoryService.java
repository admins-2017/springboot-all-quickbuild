package com.kang.sys.service;

import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface IMerchantCommodityCategoryService extends IService<MerchantCommodityCategory> {

    /**
     * 根据分类id获取对应的分类下的所有商品
     * @param categoryId
     * @return List<MerchantCommodity>
     */
    List<MerchantCommodityAndCategoryName> getCommodityWithCategory(Integer categoryId);
}
