package com.kang.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.MerchantCommodityAndCategoryName;
import com.kang.sys.entity.MerchantCommodityCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.MerchantCommodityCategoryVo;
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
     * 根据分类id查询分类下所有商品
     * @param categoryId
     * @param pages
     * @return
     */
    @Select("select a.commodity_id,a.commodity_name,a.commodity_number,a.commodity_picture,a.commodity_unit,\n" +
            "\t\ta.commodity_price,a.commodity_description,a.insert_time,b.category_name \n" +
            "\t\t\tfrom merchant_commodity a \n" +
            "\t\t\tINNER JOIN merchant_commodity_category b\n" +
            "\t\t\t\tON a.category_id= b.category_id\n" +
            "\t\t\t\tWHERE b.category_id= #{categoryId} AND b.category_status= 1 AND a.commodity_status= 0 ")
    IPage<MerchantCommodityAndCategoryName> getCommodityWithCategory(Page<MerchantCommodityAndCategoryName> pages,@Param("categoryId") Integer categoryId);


    /**
     * 分页查询所有分类
     * @param categoryPage
     * @return
     */
    IPage<MerchantCommodityCategoryVo> selectAllCategoryWithPage(Page<MerchantCommodityCategoryVo> categoryPage);

    /**
     * 查询不是一级目录的分类
     * @return
     */
    @Select("SELECT category_id, category_name, category_status, category_pid, category_level\n" +
            "        FROM merchant_commodity_category WHERE category_status = 1 and category_level !=1")
    List<MerchantCommodityCategoryVo> selectCategoryWithLevel();
}
