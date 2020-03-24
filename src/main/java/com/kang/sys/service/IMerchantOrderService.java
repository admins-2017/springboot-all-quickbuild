package com.kang.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.InsertOrderDto;
import com.kang.sys.dto.QueryOrderDto;
import com.kang.sys.entity.MerchantOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.order.CommodityWithShopVo;
import com.kang.sys.vo.order.OrderInitVo;
import com.kang.sys.vo.order.OrderWithDetailsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
public interface IMerchantOrderService extends IService<MerchantOrder> {

    /**
     * 添加销售单
     * @param orderDto
     */
    void insertOrder(InsertOrderDto orderDto);


    /**
     * 销售单初始化
     * @return
     */
    OrderInitVo initVo();


    /**
     * 根据状态查询销售单
     * @param dtoPage
     * @param pStatus
     * @return
     */
    IPage<OrderWithDetailsVo> getPageWithStatus(Page<OrderWithDetailsVo> dtoPage, Boolean pStatus);

    /**
     * 根据条件查询订单详情
     * @param dto
     * @return
     */
    IPage<OrderWithDetailsVo> getPageWithConditions(QueryOrderDto dto);


    /**
     * 根据商铺获取所有商品及库存
     * @param shopId
     * @return
     */
    List<CommodityWithShopVo> getCommodityByShopId(Long shopId);
}
