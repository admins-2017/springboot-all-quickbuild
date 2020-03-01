package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantPurchaseDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.PurchaseDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface MerchantPurchaseDetailsMapper extends BaseMapper<MerchantPurchaseDetails> {

    /**
     * 根据订单编号查询详情
     * @param purchaseNumber
     * @return
     */
    @Select("SELECT\n" +
            "        mc.commodity_name,mc.commodity_number,\n" +
            "\t\t\t\tmc.commodity_unit,\n" +
            "        mpd.purchase_quantity,mpd.refer_unit_price,\n" +
            "        mpd.before_discount,\n" +
            "        mpd.after_discount,\n" +
            "        mpd.discount_rate \n" +
            "    FROM\n" +
            "        merchant_purchase_details mpd\n" +
            "\t\tLEFT JOIN merchant_commodity mc\n" +
            "\t\t\t\ton mpd.commodity_id = mc.commodity_id\n" +
            "    WHERE\n" +
            "        mpd.purchase_number = #{purchaseNumber}")
    List<PurchaseDetailsVo> getDetailsByNumber(@Param("purchaseNumber") String purchaseNumber);
}
