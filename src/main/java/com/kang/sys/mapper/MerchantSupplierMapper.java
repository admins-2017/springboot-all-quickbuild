package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.purchase.PurchaseSupplierVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface MerchantSupplierMapper extends BaseMapper<MerchantSupplier> {

    /**
     * 更新供应商欠款
     * @param supplierId
     * @param purchaseUnpaid
     */
    @Update("UPDATE merchant_supplier SET supplier_arrears= supplier_arrears + #{purchaseUnpaid} WHERE supplier_id= #{supplierId}")
    void updateArrears(@Param("supplierId") Integer supplierId, @Param("purchaseUnpaid") BigDecimal purchaseUnpaid);

    /**
     * 初始化数据
     * @return
     */
    @Select("select supplier_id,supplier_name,supplier_address,supplier_bank_account,supplier_bank_number  \n" +
            "\tfrom merchant_supplier where supplier_status= 1")
    List<PurchaseSupplierVo> getListByInit();
}
