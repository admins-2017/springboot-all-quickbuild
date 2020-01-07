package com.kang.sys.service;

import com.kang.sys.entity.MerchantInventory;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
