package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.utils.IdRandom;
import com.kang.sys.dto.InsertPurchaseWithDetailsDto;
import com.kang.sys.dto.QueryPurchaseDto;
import com.kang.sys.entity.MerchantInventory;
import com.kang.sys.entity.MerchantPurchase;
import com.kang.sys.entity.MerchantPurchaseDetails;
import com.kang.sys.mapper.MerchantPurchaseMapper;
import com.kang.sys.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.PurchaseDetailsVo;
import com.kang.sys.vo.PurchaseInitVo;
import com.kang.sys.vo.PurchaseWithDetailsVo;
import com.kang.sys.vo.purchase.PurchaseCommodityVo;
import com.kang.sys.vo.purchase.PurchaseShopVo;
import com.kang.sys.vo.purchase.PurchaseSupplierVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@CacheConfig(cacheNames = "shop:merchant-purchase")
public class MerchantPurchaseServiceImpl extends ServiceImpl<MerchantPurchaseMapper, MerchantPurchase> implements IMerchantPurchaseService {

    @Autowired
    private IMerchantPurchaseDetailsService detailsService;
    @Autowired
    private IMerchantSupplierService supplierService;
    @Autowired
    private IMerchantInventoryService inventoryService;
    @Autowired
    private IMerchantCommodityService commodityService;
    @Autowired
    private IMerchantShopService shopService;

    /**
     * 新增进货单及更新库存
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPurchaseOrder(InsertPurchaseWithDetailsDto dto) {
        MerchantPurchase purchase = new MerchantPurchase();
        List<MerchantPurchaseDetails> children = dto.getChildren();
        if(dto.getPurchaseStatus()) {
            /**
             * 更新供应商欠款
             */
            if (dto.getPurchaseSettleStatus() == false) {
                supplierService.updateArrears(dto.getSupplierId(), dto.getPurchaseUnpaid());
            }

            /**
             * 更新库存
             */
            for (MerchantPurchaseDetails details : children) {
                Integer count = inventoryService.getCount(dto.getShopId(), details.getCommodityId());
                if (count == 0) {
                    inventoryService.save(new MerchantInventory(dto.getShopId(), details.getCommodityId(), details.getPurchaseQuantity()));
                } else {
                    inventoryService.updateInventory(dto.getShopId(), details.getCommodityId(), details.getPurchaseQuantity());
                }
            }
        }else{
            /**
             * 供应商退款
             */
            if (dto.getPurchaseSettleStatus() == false) {
                supplierService.supplierRefund(dto.getSupplierId(), dto.getPurchaseUnpaid());
            }

            /**
             * 更新库存
             */

            for (MerchantPurchaseDetails details : children) {
                /**
                 * 预留功能 退货数量不能比现有库存大
                 */
                inventoryService.recedeInventory(dto.getShopId(), details.getCommodityId(), details.getPurchaseQuantity());
            }
        }
        BeanUtils.copyProperties(dto,purchase);
        detailsService.saveBatch(children);
        this.baseMapper.insert(purchase);
    }

    @Override
    public IPage<PurchaseWithDetailsVo> getPageWithStatus(Page<PurchaseWithDetailsVo> dtoPage, Boolean pStatus) {
        IPage<PurchaseWithDetailsVo> pageWithStatus = this.baseMapper.getPageWithStatus(dtoPage, pStatus);
        List<PurchaseWithDetailsVo> records = pageWithStatus.getRecords();
        for (PurchaseWithDetailsVo detailsVo:records) {
            List<PurchaseDetailsVo> list=detailsService.getDetailsByNumber(detailsVo.getPurchaseNumber());
            detailsVo.setChildren(list);
        }
        return pageWithStatus;
    }

    @Override
    public IPage<PurchaseWithDetailsVo> getPageWithConditions(QueryPurchaseDto dto) {
        Page<PurchaseWithDetailsVo> page = new Page<>(dto.getPage(),dto.getSize());
        IPage<PurchaseWithDetailsVo> result = this.baseMapper.getPageWithConditions(page,dto);
        return result;
    }

    @Override
    public PurchaseInitVo getInit() {
        PurchaseInitVo vo = new PurchaseInitVo();
        List<PurchaseCommodityVo> commodityVoList = commodityService.getListByInit();
        List<PurchaseShopVo> shopVoList = shopService.getListByInit();
        List<PurchaseSupplierVo> listByInit = supplierService.getListByInit();
        vo.setCommodityList(commodityVoList);
        vo.setShopList(shopVoList);
        vo.setSupplierList(listByInit);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        //生成订单号
        vo.setPurchaseNumber(sdf.format(new Date())+ IdRandom.getRandom());
        return vo;
    }

}
