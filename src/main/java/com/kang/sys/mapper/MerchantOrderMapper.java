package com.kang.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.QueryOrderDto;
import com.kang.sys.entity.MerchantOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.order.CommodityWithShopVo;
import com.kang.sys.vo.order.OrderWithDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
public interface MerchantOrderMapper extends BaseMapper<MerchantOrder> {

    /**
     * 根据销售单状态查询
     * @param dtoPage
     * @param pStatus
     * @return
     */
    IPage<OrderWithDetailsVo> getOrderByStatus(Page<OrderWithDetailsVo> dtoPage, Boolean pStatus);

    /**
     * 根据查询条件来进行查询
     * @param page
     * @param dto
     * @return
     */
    IPage<OrderWithDetailsVo> getPageWithConditions(Page<OrderWithDetailsVo> page,@Param("dto") QueryOrderDto dto);

    /**
     * 根据商铺查询所有商品及库存
     * @param shopId
     * @return
     */
    @Select("select mi.inventory_number,mc.commodity_id,mc.commodity_name,mc.commodity_number,mc.commodity_picture,\n" +
            "\tmc.commodity_price,mc.commodity_unit,mc.commodity_description from merchant_inventory mi\n" +
            "\tLEFT JOIN merchant_commodity mc\n" +
            "\tON mi.commodity_id = mc.commodity_id\n" +
            "\twhere mi.shop_id= #{shopId} AND mc.commodity_status=0")
    List<CommodityWithShopVo> getCommodityByShopId(@Param("shopId") Long shopId);
}
