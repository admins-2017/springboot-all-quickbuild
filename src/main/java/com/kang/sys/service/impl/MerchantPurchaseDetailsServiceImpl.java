package com.kang.sys.service.impl;

import com.kang.sys.entity.MerchantPurchaseDetails;
import com.kang.sys.mapper.MerchantPurchaseDetailsMapper;
import com.kang.sys.service.IMerchantPurchaseDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.PurchaseDetailsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
@Service
public class MerchantPurchaseDetailsServiceImpl extends ServiceImpl<MerchantPurchaseDetailsMapper, MerchantPurchaseDetails> implements IMerchantPurchaseDetailsService {

    @Override
    public List<PurchaseDetailsVo> getDetailsByNumber(String purchaseNumber) {
        return this.baseMapper.getDetailsByNumber(purchaseNumber);
    }
}
