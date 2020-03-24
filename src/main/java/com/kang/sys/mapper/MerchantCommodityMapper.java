package com.kang.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.entity.MerchantCommodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.db.CommodityWithCategory;
import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface MerchantCommodityMapper extends BaseMapper<MerchantCommodity> {


    /**
     * 根据商品状态返回
     * @param commodityStatus 商品状态
     * @param tenantId 租户标示
     * @param pages 分页
     * @return 商品及分类名
     */
    @Select("select mc.commodity_id,mc.commodity_name,mc.commodity_number,mc.commodity_picture,mc.commodity_price,mc.commodity_unit\n" +
            "\t,mc.commodity_description,mc.insert_time,mcc.category_name \n" +
            "\tfrom merchant_commodity mc\n" +
            "  LEFT JOIN merchant_commodity_category mcc\n" +
            "\t\tON mc.category_id = mcc.category_id\n" +
            "      where mc.commodity_status=#{commodityStatus} and mc.tenant_id =#{tenantId}")
    IPage<CommodityWithCategory> selectByCommodityStatus(Page<CommodityWithCategory> pages, @Param("commodityStatus") Integer commodityStatus, @Param("tenantId") Long tenantId);

    /**
     * 用于初始化数据
     * @return
     */
    @Select("select commodity_id,commodity_name,commodity_number,commodity_picture,commodity_unit,commodity_description\n" +
            "\tfrom merchant_commodity where commodity_status!=2")
    List<PurchaseCommodityVo> getListByInit();

    /**
     * 用于初始化数据
     * @return
     */
    @Select("select commodity_id,commodity_name,commodity_number,commodity_picture,commodity_unit,commodity_description\n" +
            "\tfrom merchant_commodity where commodity_status=0")
    List<PurchaseCommodityVo> getListByInitWithOrder();

}
