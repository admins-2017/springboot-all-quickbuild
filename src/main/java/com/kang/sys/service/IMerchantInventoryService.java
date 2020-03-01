package com.kang.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.entity.MerchantInventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.ShopWithCommodity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface IMerchantInventoryService extends IService<MerchantInventory> {

    /**
     * 增加库存
     * @param number 新增数量
     * @param id    库存id
     */
    void increaseInventory(Integer number,Integer id);

    /**
     * 减去库存
     * @param number 减去数量
     * @param id    库存id
     */
    void minusInventory(Integer number,Integer id);

    /**
     * 根据商铺id查询商铺下所有商品
     * @param shopWithCommodityPage
     * @param shopId
     * @return
     */
    IPage<ShopWithCommodity> getCommodityWithShopById(Page<ShopWithCommodity> shopWithCommodityPage, Long shopId);

    /**
     * 查询库存是否存在
     * @param shopId
     * @param commodityId
     * @return
     */
    Integer getCount(Long shopId, Integer commodityId);

    /**
     * 更新库存 增加
     * @param shopId
     * @param commodityId
     * @param purchaseQuantity
     */
    void updateInventory(Long shopId, Integer commodityId, Integer purchaseQuantity);

    /**
     * 退货 减去库存
     * @param shopId
     * @param commodityId
     * @param purchaseQuantity
     */
    void recedeInventory(Long shopId, Integer commodityId, Integer purchaseQuantity);
}
