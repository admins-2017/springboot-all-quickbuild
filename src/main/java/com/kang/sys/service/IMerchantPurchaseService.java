package com.kang.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.InsertPurchaseWithDetailsDto;
import com.kang.sys.dto.QueryPurchaseDto;
import com.kang.sys.entity.MerchantPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.PurchaseInitVo;
import com.kang.sys.vo.PurchaseWithDetailsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public interface IMerchantPurchaseService extends IService<MerchantPurchase> {

    /**
     * 新增进货单
     * @param dto
     */
    void addPurchaseOrder(InsertPurchaseWithDetailsDto dto);

    /**
     * 根据状态查询进货/退货
     * @param dtoPage
     * @param pStatus
     * @return
     */
    IPage<PurchaseWithDetailsVo> getPageWithStatus(Page<PurchaseWithDetailsVo> dtoPage, Boolean pStatus);

    /**
     * 根据查询条件进行分页查询
     * @param dto
     * @return
     */
    IPage<PurchaseWithDetailsVo> getPageWithConditions(QueryPurchaseDto dto);

    /**
     * 加载初始化订单数据
     * @return
     */
    PurchaseInitVo getInit();

    /**
     * 作废进货单
     * @param id
     */
    void invalidPurchase(String number);
}
