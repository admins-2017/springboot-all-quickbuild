package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantOrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.order.OrderDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-21
 */
public interface MerchantOrderDetailsMapper extends BaseMapper<MerchantOrderDetails> {

    /**
     * 根据销售单号查询详情
     * @param number
     * @return
     */
    List<OrderDetailsVo> getDetailsByNumber(@Param("orderNumber") String number);
}
