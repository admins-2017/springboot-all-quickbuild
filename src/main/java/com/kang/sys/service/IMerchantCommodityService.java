package com.kang.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.MerchantCommodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.db.CommodityWithCategory;
import com.kang.sys.vo.purchase.PurchaseCommodityVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface IMerchantCommodityService extends IService<MerchantCommodity> {


    /**
     * 根据商品状态进行查询
     * @param pages
     * @param commodityStatus
     * @return
     */
    IPage<CommodityWithCategory> selectByCommodityStatus(Page<CommodityWithCategory> pages, Integer commodityStatus);

    /**
     * 用于加载进货单初始供应商
     * @return
     */
    List<PurchaseCommodityVo> getListByInit();


}
