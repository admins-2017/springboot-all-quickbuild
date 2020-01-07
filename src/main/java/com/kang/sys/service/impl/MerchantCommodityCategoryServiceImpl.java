package com.kang.sys.service.impl;

import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.kang.sys.mapper.MerchantCommodityCategoryMapper;
import com.kang.sys.service.IMerchantCommodityCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@Service
public class MerchantCommodityCategoryServiceImpl extends ServiceImpl<MerchantCommodityCategoryMapper, MerchantCommodityCategory> implements IMerchantCommodityCategoryService {



    @Override
    public List<MerchantCommodityAndCategoryName> getCommodityWithCategory(Integer categoryId) {
        return this.baseMapper.getCommodityWithCategory(categoryId);
    }
}
