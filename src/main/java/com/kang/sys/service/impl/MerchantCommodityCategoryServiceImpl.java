package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.imploded.utils.CategoryTreeUtil;
import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.kang.sys.mapper.MerchantCommodityCategoryMapper;
import com.kang.sys.service.IMerchantCommodityCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.MerchantCommodityCategoryVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@CacheConfig(cacheNames = "shop:commodity-category")
public class MerchantCommodityCategoryServiceImpl extends ServiceImpl<MerchantCommodityCategoryMapper, MerchantCommodityCategory> implements IMerchantCommodityCategoryService {

    @Override
    @Cacheable(key = "#pages.current+':'+#pages.size+':'+#categoryId")
    public IPage<MerchantCommodityAndCategoryName> getCommodityWithCategory(Page<MerchantCommodityAndCategoryName> pages,Integer categoryId) {
        return this.baseMapper.getCommodityWithCategory(pages,categoryId);
    }

    @Override
    @Cacheable(key = "#root.method+':'+#categoryPage.current+':'+#categoryPage.size")
    public IPage<MerchantCommodityCategoryVo> getAllCategoryWithPage(Page<MerchantCommodityCategoryVo> categoryPage) {
        //构建一级分类菜单分页
        IPage<MerchantCommodityCategoryVo> voIPage = this.baseMapper.selectAllCategoryWithPage(categoryPage);
        //查询非一级菜单分类
        List<MerchantCommodityCategoryVo> voList=this.baseMapper.selectCategoryWithLevel();
        List<MerchantCommodityCategoryVo> categoryList = CategoryTreeUtil.parseCatagoryTree(voList);


        for (int i = 0; i <voIPage.getRecords().size() ; i++) {
            List<MerchantCommodityCategoryVo> childrenList = new ArrayList<>();
            for (int j = 0; j <categoryList.size() ; j++) {

                if (voIPage.getRecords().get(i).getCategoryId().equals(categoryList.get(j).getCategoryPid())){

                    childrenList.add(categoryList.get(j));
                    voIPage.getRecords().get(i).setChildren(childrenList);
                }
            }
        }



        return voIPage;
    }
}
