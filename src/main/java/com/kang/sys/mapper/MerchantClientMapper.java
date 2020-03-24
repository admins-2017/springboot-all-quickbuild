package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantClient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.order.OrderClientVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
public interface MerchantClientMapper extends BaseMapper<MerchantClient> {

    /**
     * 用于初始化销售单
     * @return
     */
    @Select("select client_id,client_name,client_address,client_bank_account,client_bank_number \n" +
            "\t\tfrom merchant_client where client_status = 1")
    List<OrderClientVo> getListByInitWithOrder();
}
