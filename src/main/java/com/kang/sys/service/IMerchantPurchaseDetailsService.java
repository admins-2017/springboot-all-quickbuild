package com.kang.sys.service;

import com.kang.sys.entity.MerchantPurchaseDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.PurchaseDetailsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface IMerchantPurchaseDetailsService extends IService<MerchantPurchaseDetails> {

    /**
     * 根据单号查询详情
     * @param purchaseNumber
     * @return
     */
    List<PurchaseDetailsVo> getDetailsByNumber(String purchaseNumber);
}
