package com.kang.sys.service;

import com.kang.sys.entity.MerchantSupplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.purchase.PurchaseSupplierVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface IMerchantSupplierService extends IService<MerchantSupplier> {

    /**
     * 更新供应商欠款 增加
     * @param supplierId
     * @param purchaseUnpaid
     */
    void updateArrears(Integer supplierId, BigDecimal purchaseUnpaid);

    /**
     * 更新供应商欠款 退货减去供应商未付款
     * @param supplierId
     * @param purchaseUnpaid
     */
    void supplierRefund(Integer supplierId, BigDecimal purchaseUnpaid);

    /**
     * 用于加载进货单初始供应商
     * @return
     */
    List<PurchaseSupplierVo> getListByInit();
}
