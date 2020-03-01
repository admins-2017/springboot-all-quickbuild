package com.kang.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.MerchantCommodityCategoryVo;

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
    IPage<MerchantCommodityAndCategoryName> getCommodityWithCategory(Page<MerchantCommodityAndCategoryName> pages,Integer categoryId);

    /**
     * 分页查询所有分类
     * @param categoryPage
     * @return
     */
    IPage<MerchantCommodityCategoryVo> getAllCategoryWithPage(Page<MerchantCommodityCategoryVo> categoryPage);
}
