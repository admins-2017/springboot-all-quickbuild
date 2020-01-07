package com.kang.sys.service.impl;

import com.kang.sys.entity.MerchantInventory;
import com.kang.sys.mapper.MerchantInventoryMapper;
import com.kang.sys.service.IMerchantInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
