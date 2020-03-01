package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.entity.MerchantInventory;
import com.kang.sys.mapper.MerchantInventoryMapper;
import com.kang.sys.service.IMerchantInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.ShopWithCommodity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
@Service
public class MerchantInventoryServiceImpl extends ServiceImpl<MerchantInventoryMapper, MerchantInventory> implements IMerchantInventoryService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseInventory(Integer number, Integer id) {
        this.baseMapper.increaseInventory(number, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void minusInventory(Integer number, Integer id) {
        this.baseMapper.minusInventory( number, id);
    }

    @Override
    public IPage<ShopWithCommodity> getCommodityWithShopById(Page<ShopWithCommodity> shopWithCommodityPage, Long shopId) {
        return this.baseMapper.getCommodityWithShopById(shopWithCommodityPage,shopId);
    }

    @Override
    public Integer getCount(Long shopId, Integer commodityId) {
        return this.baseMapper.selectCount(new QueryWrapper<MerchantInventory>()
                .eq("shop_id",shopId).eq("commodity_id",commodityId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInventory(Long shopId, Integer commodityId, Integer purchaseQuantity) {
        this.baseMapper.update(null,new UpdateWrapper<MerchantInventory>()
                .setSql("inventory_number = inventory_number +"+purchaseQuantity)
                .eq("shop_id",shopId).eq("commodity_id",commodityId));
    }

    @Override
    public void recedeInventory(Long shopId, Integer commodityId, Integer purchaseQuantity) {
        this.baseMapper.update(null,new UpdateWrapper<MerchantInventory>()
                .setSql("inventory_number = inventory_number -"+purchaseQuantity)
                .eq("shop_id",shopId).eq("commodity_id",commodityId));
    }
}
