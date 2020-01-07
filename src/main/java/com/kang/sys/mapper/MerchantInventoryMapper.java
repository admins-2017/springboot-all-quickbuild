package com.kang.sys.mapper;

import com.kang.sys.entity.MerchantInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface MerchantInventoryMapper extends BaseMapper<MerchantInventory> {

    /**
     * 增加库存
     * @param number
     * @param id
     */
    @Update("UPDATE merchant_inventory SET inventory_number= " +
            "(inventory_number + #{number}) WHERE inventory_id= #{id} ")
    void increaseInventory(@Param("number") Integer number, @Param("id") Integer id);

    /**
     * 减少库存
     * @param number
     * @param id
     */
    @Update("UPDATE merchant_inventory SET inventory_number= " +
            "(inventory_number - #{number}) WHERE inventory_id= #{id} ")
    void minusInventory(@Param("number") Integer number, @Param("id") Integer id);
}
