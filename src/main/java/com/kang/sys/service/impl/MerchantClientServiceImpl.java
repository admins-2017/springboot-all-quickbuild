package com.kang.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kang.sys.entity.MerchantClient;
import com.kang.sys.mapper.MerchantClientMapper;
import com.kang.sys.service.IMerchantClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.sys.vo.order.OrderClientVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@Service
public class MerchantClientServiceImpl extends ServiceImpl<MerchantClientMapper, MerchantClient> implements IMerchantClientService {

    @Override
    public void updateArrears(Integer clientId, BigDecimal orderUnpaid) {
        this.baseMapper.update(null,new UpdateWrapper<MerchantClient>()
                .setSql("client_arrears = client_arrears +"+orderUnpaid).eq("client_id",clientId));
    }

    @Override
    public void orderRefund(Integer clientId, BigDecimal orderUnpaid) {
        this.baseMapper.update(null,new UpdateWrapper<MerchantClient>()
                .setSql("client_arrears = client_arrears -"+orderUnpaid).eq("client_id",clientId));
    }

    @Override
    public List<OrderClientVo> getListByInitWithOrder() {
        return this.baseMapper.getListByInitWithOrder();
    }
}
