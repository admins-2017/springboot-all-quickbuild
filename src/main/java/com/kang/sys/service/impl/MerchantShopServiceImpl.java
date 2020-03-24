package com.kang.sys.service.impl;

import com.kang.sys.entity.MerchantShop;
import com.kang.sys.mapper.MerchantShopMapper;
import com.kang.sys.service.IMerchantShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.purchase.PurchaseShopVo;
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
public class MerchantShopServiceImpl extends ServiceImpl<MerchantShopMapper, MerchantShop> implements IMerchantShopService {

    @Override
    public List<PurchaseShopVo> getListByInit() {
        return this.baseMapper.getListByInit();
    }

    @Override
    public List<PurchaseShopVo> getListByInitWithOrder() {
        return this.baseMapper.getListByInitWithOrder();
    }


}
