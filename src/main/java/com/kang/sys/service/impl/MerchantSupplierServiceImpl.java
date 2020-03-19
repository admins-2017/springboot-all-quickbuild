package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.sys.entity.MerchantSupplier;
import com.kang.sys.mapper.MerchantSupplierMapper;
import com.kang.sys.service.IMerchantSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.purchase.PurchaseSupplierVo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class MerchantSupplierServiceImpl extends ServiceImpl<MerchantSupplierMapper, MerchantSupplier> implements IMerchantSupplierService {

    @Override
    public void updateArrears(Integer supplierId, BigDecimal purchaseUnpaid) {
        this.baseMapper.updateArrears(supplierId,purchaseUnpaid);
    }

    @Override
    public void supplierRefund(Integer supplierId, BigDecimal purchaseUnpaid) {
        this.baseMapper.update(null,new UpdateWrapper<MerchantSupplier>()
            .setSql("supplier_arrears = supplier_arrears -"+purchaseUnpaid).eq("supplier_id",supplierId));
    }

    @Override
    public List<PurchaseSupplierVo> getListByInit() {
        return this.baseMapper.getListByInit();
    }
}
