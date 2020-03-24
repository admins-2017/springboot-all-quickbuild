package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantShop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.purchase.PurchaseShopVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface MerchantShopMapper extends BaseMapper<MerchantShop> {

    /**
     * 进货单初始化数据
     * @return
     */
    @Select("select shop_id,shop_name,shop_address\n" +
            "\tfrom merchant_shop where shop_status!=3")
    List<PurchaseShopVo> getListByInit();


    /**
     * 进货单初始化数据
     * @return
     */
    @Select("select shop_id,shop_name,shop_address\n" +
            "\tfrom merchant_shop where shop_status=1")
    List<PurchaseShopVo> getListByInitWithOrder();

}
