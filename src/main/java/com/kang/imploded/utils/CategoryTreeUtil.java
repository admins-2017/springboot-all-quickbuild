package com.kang.imploded.utils;

import com.kang.sys.vo.MerchantCommodityCategoryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/15 10:45
 */
public class CategoryTreeUtil {
    /**
     * @方法名: parseMenuTree<br>
     * @描述: 组装菜单<br>
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     */
    public static List<MerchantCommodityCategoryVo> parseCatagoryTree(List<MerchantCommodityCategoryVo> list){
        List<MerchantCommodityCategoryVo> result = new ArrayList<>();

        // 1、获取第一级节点
        for (MerchantCommodityCategoryVo categoryVo : list) {
            if(2 == categoryVo.getCategoryLevel()) {
                result.add(categoryVo);
            }
        }

        // 2、递归获取子节点
        for (MerchantCommodityCategoryVo parent : result) {
            parent = recursiveTree(parent, list);
        }

        return result;
    }

    public static MerchantCommodityCategoryVo recursiveTree(MerchantCommodityCategoryVo parent, List<MerchantCommodityCategoryVo> list) {
        for (MerchantCommodityCategoryVo categoryVo : list) {
            if(parent.getCategoryId() .equals( categoryVo.getCategoryPid())) {
                categoryVo = recursiveTree(categoryVo, list);
                parent.getChildren().add(categoryVo);
            }
        }

        return parent;
    }
}
