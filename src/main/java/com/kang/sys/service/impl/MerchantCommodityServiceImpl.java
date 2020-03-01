package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.MerchantCommodity;
import com.kang.sys.mapper.MerchantCommodityMapper;
import com.kang.sys.service.IMerchantCommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.db.CommodityWithCategory;
import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import org.springframework.scheduling.annotation.Async;
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
public class MerchantCommodityServiceImpl extends ServiceImpl<MerchantCommodityMapper, MerchantCommodity> implements IMerchantCommodityService {

    @Override
    public IPage<CommodityWithCategory> selectByCommodityStatus(Page<CommodityWithCategory> pages, Integer commodityStatus) {
        return this.baseMapper.selectByCommodityStatus(pages,commodityStatus, SecurityUntil.getTenantId());
    }

    @Override
    public List<PurchaseCommodityVo> getListByInit() {
        return this.baseMapper.getListByInit();
    }
}
