package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.InsertOrderDto;
import com.kang.sys.dto.QueryOrderDto;
import com.kang.sys.entity.MerchantInventory;
import com.kang.sys.entity.MerchantOrder;
import com.kang.sys.entity.MerchantOrderDetails;
import com.kang.sys.entity.MerchantPurchaseDetails;
import com.kang.sys.mapper.MerchantOrderMapper;
import com.kang.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.order.CommodityWithShopVo;
import com.kang.sys.vo.order.OrderClientVo;
import com.kang.sys.vo.order.OrderInitVo;
import com.kang.sys.vo.order.OrderWithDetailsVo;
import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import com.kang.sys.vo.purchase.PurchaseShopVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
@Service
public class MerchantOrderServiceImpl extends ServiceImpl<MerchantOrderMapper, MerchantOrder> implements IMerchantOrderService {

    @Autowired
    private IMerchantOrderDetailsService detailsService;
    @Autowired
    private IMerchantClientService clientService;
    @Autowired
    private IMerchantInventoryService inventoryService;
    @Autowired
    private IMerchantCommodityService commodityService;
    @Autowired
    private IMerchantShopService shopService;
    @Autowired
    private RedisOperator redisOperator;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOrder(InsertOrderDto orderDto) {
        MerchantOrder order = new MerchantOrder();
        List<MerchantOrderDetails> detailsList = orderDto.getChildren();
        if (orderDto.getOrderStatus()){
            /**
             * 更新客户欠款
             */
            if (orderDto.getOrderSettleStatus() == false) {
                clientService.updateArrears(orderDto.getClientId(), orderDto.getOrderUnpaid());
            }
            /**
             * 更新库存
             */
            for (MerchantOrderDetails details : detailsList) {
                    inventoryService.recedeInventory(orderDto.getShopId(), details.getCommodityId(), details.getBuyNumber());
            }

        }else {
            /**
             * 供应商退款
             */
            if (orderDto.getOrderSettleStatus() == false) {
                clientService.orderRefund(orderDto.getClientId(), orderDto.getOrderUnpaid());
            }

            /**
             * 更新库存
             */
            for (MerchantOrderDetails details : detailsList) {
                Integer count = inventoryService.getCount(orderDto.getShopId(), details.getCommodityId());
                if (count == 0) {
                    inventoryService.save(new MerchantInventory(orderDto.getShopId(), details.getCommodityId(), details.getBuyNumber()));
                } else {
                    inventoryService.updateInventory(orderDto.getShopId(), details.getCommodityId(), details.getBuyNumber());
                }
            }
        }
        BeanUtils.copyProperties(orderDto,order);
        detailsService.saveBatch(detailsList);
        this.baseMapper.insert(order);
    }


    @Override
    public OrderInitVo initVo(){
        OrderInitVo vo = new OrderInitVo();
        List<PurchaseShopVo> shops = shopService.getListByInitWithOrder();
        List<OrderClientVo> clients= clientService.getListByInitWithOrder();
        vo.setClientList(clients);
        vo.setShopList(shops);
        return vo;
    }

    @Override
    public IPage<OrderWithDetailsVo> getPageWithStatus(Page<OrderWithDetailsVo> dtoPage, Boolean pStatus) {
        return this.baseMapper.getOrderByStatus(dtoPage,pStatus);
    }

    @Override
    public IPage<OrderWithDetailsVo> getPageWithConditions(QueryOrderDto dto) {
        Page<OrderWithDetailsVo> page = new Page<>(dto.getPage(),dto.getSize());
        return this.baseMapper.getPageWithConditions(page,dto);
    }

    @Override
    public List<CommodityWithShopVo> getCommodityByShopId(Long shopId) {
        return this.baseMapper.getCommodityByShopId(shopId);
    }

}
