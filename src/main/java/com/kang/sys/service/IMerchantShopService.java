package com.kang.sys.service;

import com.kang.sys.entity.MerchantShop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.purchase.PurchaseShopVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface IMerchantShopService extends IService<MerchantShop> {

    /**
     * 用于进货单初始化数据
     * @return
     */
    List<PurchaseShopVo> getListByInit();


    /**
     * 用于销售单初始化
     * @return
     */
    List<PurchaseShopVo> getListByInitWithOrder();
}
