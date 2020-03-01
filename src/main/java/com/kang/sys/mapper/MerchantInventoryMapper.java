package com.kang.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.entity.MerchantInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.ShopWithCommodity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface MerchantInventoryMapper extends BaseMapper<MerchantInventory> {

    /**
     * 增加库存
     * @param number
     * @param id
     */
    @Update("UPDATE merchant_inventory SET inventory_number= " +
            "(inventory_number + #{number}) WHERE inventory_id= #{id} ")
    void increaseInventory(@Param("number") Integer number, @Param("id") Integer id);

    /**
     * 减少库存
     * @param number
     * @param id
     */
    @Update("UPDATE merchant_inventory SET inventory_number= " +
            "(inventory_number - #{number}) WHERE inventory_id= #{id} ")
    void minusInventory(@Param("number") Integer number, @Param("id") Integer id);

    /**
     * 根据id查询商铺下所有商品
     * @param shopWithCommodityPage
     * @param shopId
     * @return
     */
    @Select("select ms.shop_name,ms.shop_address,ms.shop_status,mi.inventory_number,mc.commodity_name,mc.commodity_number,mc.commodity_price,mc.commodity_picture,mc.commodity_unit from \n" +
            "\tmerchant_inventory mi\n" +
            "\tLEFT JOIN merchant_shop ms \n" +
            "\t\tON mi.shop_id = ms.shop_id\n" +
            "\tLEFT JOIN merchant_commodity mc\n" +
            "\t\ton mi.commodity_id = mc.commodity_id\n" +
            "\twhere mi.shop_id=#{shopId}")
    IPage<ShopWithCommodity> getCommodityWithShopById(Page<ShopWithCommodity> shopWithCommodityPage, @Param("shopId") Long shopId);
}
