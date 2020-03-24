package com.kang.sys.service;

import com.kang.sys.dto.InsertOrderDto;
import com.kang.sys.entity.MerchantClient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kang.sys.vo.order.OrderClientVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
public interface IMerchantClientService extends IService<MerchantClient> {

    /**
     * 更新客户欠款
     * @param clientId
     * @param orderUnpaid
     */
    void updateArrears(Integer clientId, BigDecimal orderUnpaid);

    /**
     * 客户退款
     * @param clientId
     * @param orderUnpaid
     */
    void orderRefund(Integer clientId, BigDecimal orderUnpaid);

    /**
     * 销售单初始化
     * @return
     */
    List<OrderClientVo> getListByInitWithOrder();
}
