package com.kang.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.QueryPurchaseDto;
import com.kang.sys.entity.MerchantPurchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.PurchaseWithDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface MerchantPurchaseMapper extends BaseMapper<MerchantPurchase> {

    /**
     * 根据状态返回数据
     * @param dtoPage
     * @param pStatus
     * @return
     */
    @Select("select mp.purchase_id,mp.purchase_number,mp.purchase_total_amount,mp.purchase_status\n" +
            "\t\t,mp.purchase_pay,mp.purchase_unpaid,mp.purchase_settle_status,mp.purchase_pay_type\n" +
            "\t\t,mp.insert_time\n" +
            "\t\t,su.username,ms.supplier_name,msp.shop_name from merchant_purchase mp\n" +
            "\t\tLEFT JOIN sys_user su\n" +
            "\t\t\tON mp.insert_user = su.user_id\n" +
            "\t\tLEFT JOIN merchant_supplier ms\n" +
            "\t\t\tON mp.supplier_id = ms.supplier_id\n" +
            "\t\tLEFT JOIN merchant_shop msp\n" +
            "\t\t\tON mp.shop_id = msp.shop_id\n" +
            "\t\t\twhere purchase_status = #{pStatus}")
    IPage<PurchaseWithDetailsVo> getPageWithStatus(Page<PurchaseWithDetailsVo> dtoPage, @Param("pStatus") Boolean pStatus);

    /**
     * 根据条件进行分页查询
     * @param page
     * @param dto
     * @return
     */
    IPage<PurchaseWithDetailsVo> getPageWithConditions(Page<PurchaseWithDetailsVo> page, @Param("dto") QueryPurchaseDto dto);
}
