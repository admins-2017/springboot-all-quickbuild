package com.kang.sys.mapper;

import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface MerchantCommodityCategoryMapper extends BaseMapper<MerchantCommodityCategory> {

    /**
     * 根据商品分类获取分类下所有商品
     * @param categoryId
     * @return
     */
    @Select("select a.commodity_id,a.commodity_name,a.commodity_picture,a.commodity_unit,\n" +
            "\t\ta.commodity_price,a.commodity_description,a.insert_time,b.category_name \n" +
            "\t\t\tfrom merchant_commodity a \n" +
            "\t\t\tINNER JOIN merchant_commodity_category b\n" +
            "\t\t\t\tON a.category_id= b.category_id\n" +
            "\t\t\t\tWHERE b.category_id= #{categoryId} AND b.category_status= 1 AND a.commodity_status= 0")
    List<MerchantCommodityAndCategoryName> getCommodityWithCategory(@Param("categoryId") Integer categoryId);
}
