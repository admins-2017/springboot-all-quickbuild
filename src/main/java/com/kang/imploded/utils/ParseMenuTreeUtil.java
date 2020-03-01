package com.kang.imploded.utils;

import com.kang.sys.vo.MenuTreeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/1/15 10:56
 */
public class ParseMenuTreeUtil {

    /**
     * @方法名: parseMenuTree<br>
     * @描述: 组装菜单<br>
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     */
    public static List<MenuTreeVo> parseMenuTree(List<MenuTreeVo> list){
        List<MenuTreeVo> result = new ArrayList<>();

        // 1、获取第一级节点
        for (MenuTreeVo menu : list) {
            if(0 == menu.getParentId()) {
                result.add(menu);
            }
        }

        // 2、递归获取子节点
        for (MenuTreeVo parent : result) {
            parent = recursiveTree(parent, list);
        }

        return result;
    }

    public static MenuTreeVo recursiveTree(MenuTreeVo parent, List<MenuTreeVo> list) {
        for (MenuTreeVo menu : list) {
            if(parent.getMenuId() .equals( menu.getParentId())) {
                menu = recursiveTree(menu, list);
                parent.getChildren().add(menu);
            }
        }

        return parent;
    }

}
